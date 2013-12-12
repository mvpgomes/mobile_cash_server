package com.sirs.mobilecashserver.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

@Path("buy")
public class BuyService {

	private final String url = "https://sodamachine.herokuapp.com/api/delivery/";
	private final MobileCashServerDB db = MobileCashServerDB.getInstance();
	private final ConnectionFactory connFactory = ConnectionFactory
			.getInstance();
	private final DigitalSignatureManager signManager = DigitalSignatureManager
			.getInstance();

	private boolean isFresh(long timestamp) {
		DateTime currentTime = new DateTime();
		DateTime receivedTime = new DateTime(timestamp);

		DateTime minTime = currentTime.minusMinutes(1);

		if (receivedTime.isAfter(minTime.getMillis())
				&& receivedTime.isBeforeNow()) {
			return true;
		}
		return false;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response buy(Payment payment) throws MalformedURLException,
			IOException, InvalidKeyException, NoSuchAlgorithmException,
			SignatureException, JSONException {

		if (!signManager.verifySignature(payment, "RSA", "SHA1",
				GetClientPublicKey.getMobileCashAndroidPublicKey())) {
			return new ErrorResponse("Error verifying signature");
		}

		if (!isFresh(payment.getTimestamp())) {
			return new ErrorResponse("Message out of time");
		}

		User user = db.login(payment.getUsername(), payment.getPassword());

		if (user != null) {
			Product product = db.getProduct(payment.getProduct());
			BankAccount account = db.getAccount(payment.getUsername());

			if (product == null) {
				return new ErrorResponse("Product " + payment.getProduct()
						+ " does not exist");
			}

			// Check if the user can buy this product
			if (account.getBalance() >= product.getPrice()) {
				return requestProduct(payment, account, product);
			}
			return new ErrorResponse("Insufficient balance");

		}
		return new ErrorResponse("Wrong username/password");

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Payment buy() {
		return new Payment("test", "test", "test", 0, "hash");
	}

	private Response requestProduct(Payment payment, BankAccount account,
			Product product) throws MalformedURLException, IOException,
			JSONException {

		HttpsURLConnection conn = connFactory.createConnection(url);
		ConnectionManager cm = ConnectionManager.getInstance();
		JSONObject json = new JSONObject();
		try {
			json.put("deliveryMessage",
					Encryption.encrypt(payment.getProduct()));
		} catch (Exception e) {
			return new ErrorResponse("Encryption failed");
		}
		cm.sendPUT(conn, json.toString());
		String response = cm.readConnection(conn);
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
			return new PaymentResponse(payment.getUsername(),
					payment.getProduct(), account.getBalance());
		}
	}
}
