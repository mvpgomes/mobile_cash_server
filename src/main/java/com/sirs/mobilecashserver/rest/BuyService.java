package com.sirs.mobilecashserver.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jettison.json.JSONException;
import org.joda.time.DateTime;
import org.json.JSONObject;

import com.sirs.mobilecashserver.conn.ConnectionFactory;
import com.sirs.mobilecashserver.conn.ConnectionManager;
import com.sirs.mobilecashserver.db.MobileCashServerDB;
import com.sirs.mobilecashserver.rest.models.BankAccount;
import com.sirs.mobilecashserver.rest.models.ErrorResponse;
import com.sirs.mobilecashserver.rest.models.Payment;
import com.sirs.mobilecashserver.rest.models.PaymentResponse;
import com.sirs.mobilecashserver.rest.models.Product;
import com.sirs.mobilecashserver.rest.models.Response;
import com.sirs.mobilecashserver.rest.models.User;
import com.sirs.mobilecashserver.security.DigitalSignatureManager;
import com.sirs.mobilecashserver.security.Encryption;

/**
 * The Class BuyService.
 */
@Path("buy")
public class BuyService {

    /** The seed. */
    private final byte[] seed = new byte[16];

    /** The url. */
    private final String url = "https://sodamachine.herokuapp.com/api/delivery/";

    /** The db. */
    private final MobileCashServerDB db = MobileCashServerDB.getInstance();

    /** The conn factory. */
    private final ConnectionFactory connFactory = ConnectionFactory.getInstance();

    /** The sign manager. */
    private final DigitalSignatureManager signManager = DigitalSignatureManager.getInstance();

    /**
     * Checks if is fresh.
     * 
     * @param timestamp the timestamp
     * @return true, if is fresh
     */
    private boolean isFresh(long timestamp) {
        DateTime currentTime = new DateTime();
        DateTime receivedTime = new DateTime(timestamp);

        DateTime minTime = currentTime.minusMinutes(1);

        if (receivedTime.isAfter(minTime.getMillis())) {
            return true;
        }
        return false;
    }

    /**
     * Buy.
     * 
     * @param payment the payment
     * @return the response
     * @throws MalformedURLException the malformed url exception
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws InvalidKeyException the invalid key exception
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws SignatureException the signature exception
     * @throws JSONException the jSON exception
     */
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response buy(Payment payment) throws MalformedURLException, IOException, InvalidKeyException,
            NoSuchAlgorithmException, SignatureException, JSONException {

        if (!isFresh(payment.getTimestamp())) {
            return new ErrorResponse("Message out of time");
        }

        if (!signManager.verifySignature(payment, "RSA", "SHA1", DigitalSignatureManager.getMobileCashAndroidPublicKey())) {
            return new ErrorResponse("Error verifying signature");
        }

        User user = db.login(payment.getUsername(), payment.getPassword());

        if (user != null) {
            Product product = db.getProduct(payment.getProduct());
            BankAccount account = db.getAccount(payment.getUsername());

            if (product == null) {
                return new ErrorResponse("Product " + payment.getProduct() + " does not exist");
            }

            // Check if the user can buy this product
            if (account.getBalance() >= product.getPrice()) {
                return requestProduct(payment, account, product);
            }
            return new ErrorResponse("Insufficient balance");

        }
        return new ErrorResponse("Wrong username/password");

    }

    /**
     * Request product.
     * 
     * @param payment the payment
     * @param account the account
     * @param product the product
     * @return the response
     * @throws MalformedURLException the malformed url exception
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws JSONException the jSON exception
     */
    private Response requestProduct(Payment payment, BankAccount account, Product product) throws MalformedURLException,
            IOException, JSONException {

        HttpsURLConnection conn;

        try {
            conn = connFactory.createConnection(url);
        } catch (Exception e) {
            return new ErrorResponse("The machine is out of service");
        }
        ConnectionManager cm = ConnectionManager.getInstance();
        JSONObject json = new JSONObject();
        DateTime currentTime = new DateTime();
        long timestamp = currentTime.getMillis();

        try {
            // creates a random array
            new Random().nextBytes(seed);
            json.put("product", payment.getProduct());
            json.put("timestamp", timestamp);
            json.put("seed", new String(Base64.encodeBase64(seed)));
            json.put("cyphered", Encryption.encrypt(payment.getProduct() + timestamp, seed));
        } catch (Exception e) {
            return new ErrorResponse("Encryption failed " + e.getMessage());
        }
        cm.sendPUT(conn, json.toString());
        String response = cm.readConnection(conn);
        conn.disconnect();
        System.out.println("Received response from machine " + response);
        JSONObject responseJSON = new JSONObject(response);
        String responseType = responseJSON.getString("type");
        String responseMessage = responseJSON.getString("message");
        if (responseType.equals("ERROR")) {
            return new ErrorResponse(responseMessage);
        } else {
            double oldBalance = account.getBalance();
            double newBalance = oldBalance - product.getPrice();
            account.setBalance(newBalance);
            return new PaymentResponse(payment.getUsername(), payment.getProduct(), account.getBalance());
        }
    }
}
