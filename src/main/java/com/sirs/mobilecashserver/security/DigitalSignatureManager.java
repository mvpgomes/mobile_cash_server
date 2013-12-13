package com.sirs.mobilecashserver.security;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import org.json.JSONException;

import com.sirs.mobilecashserver.rest.models.Payment;
import com.sun.jersey.core.util.Base64;

/**
 * The Class DigitalSignatureManager.
 */
public class DigitalSignatureManager {

    /** The mobile cash android public key. */
    private static PublicKey mobileCashAndroidPublicKey;

    /** The instance. */
    private static DigitalSignatureManager instance;

    /**
     * Instantiates a new digital signature manager.
     */
    public DigitalSignatureManager() {
    }

    /**
     * Hash.
     * 
     * @param stringToHash
     *            the string to hash
     * @param algorithm
     *            the algorithm
     * @return the byte[]
     * @throws NoSuchAlgorithmException
     *             the no such algorithm exception
     */
    public byte[] hash(String stringToHash, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(stringToHash.getBytes());
        return digest.digest();
    }

    /**
     * Verify signature.
     * 
     * @param payment
     *            the payment
     * @param signAlgorithm
     *            the sign algorithm
     * @param hashAlgorithm
     *            the hash algorithm
     * @param pKey
     *            the key
     * @return true, if successful
     * @throws NoSuchAlgorithmException
     *             the no such algorithm exception
     * @throws InvalidKeyException
     *             the invalid key exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws SignatureException
     *             the signature exception
     * @throws JSONException
     *             the jSON exception
     */
    public boolean verifySignature(Payment payment, String signAlgorithm, String hashAlgorithm, PublicKey pKey)
            throws NoSuchAlgorithmException, InvalidKeyException, IOException, SignatureException, JSONException {
        byte[] signBytes = Base64.decode(payment.getHash().getBytes());
        Signature signature = Signature.getInstance(hashAlgorithm + "with" + signAlgorithm);
        String notSignedMessage = getNotSignedMessage(payment);
        byte[] buffer = notSignedMessage.getBytes();
        signature.initVerify(pKey);
        signature.update(buffer);
        return signature.verify(signBytes);
    }

    /**
     * Gets the single instance of DigitalSignatureManager.
     * 
     * @return single instance of DigitalSignatureManager
     */
    public static DigitalSignatureManager getInstance() {
        if (DigitalSignatureManager.instance == null) {
            DigitalSignatureManager.instance = new DigitalSignatureManager();
        }
        return DigitalSignatureManager.instance;
    }

    /**
     * Gets the not signed message.
     * 
     * @param payment the payment
     * @return the not signed message
     */
    private String getNotSignedMessage(Payment payment) {
        return payment.getUsername() + payment.getPassword() + payment.getProduct() + payment.getTimestamp();
    }

    /**
     * Gets the mobile cash android public key.
     * 
     * @return the mobile cash android public key
     */
    public static PublicKey getMobileCashAndroidPublicKey() {
        return mobileCashAndroidPublicKey;
    }

    /**
     * Sets the mobile cash android public key.
     * 
     * @param mobileCashAndroidPublicKey the new mobile cash android public key
     */
    public static void setMobileCashAndroidPublicKey(PublicKey mobileCashAndroidPublicKey) {
        DigitalSignatureManager.mobileCashAndroidPublicKey = mobileCashAndroidPublicKey;
    }

}
