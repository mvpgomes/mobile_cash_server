package com.sirs.mobilecashserver.security;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sirs.mobilecashserver.rest.models.ErrorResponse;
import com.sirs.mobilecashserver.rest.models.PublicKeyResponse;
import com.sirs.mobilecashserver.rest.models.Response;

/**
 * The Class KeyPairGenerator.
 */
public class KeyPairGenerator {

    /** The mobile cash android public key. */
    private static PublicKey mobileCashAndroidPublicKey;

    /**
     * Generate public key.
     * 
     * @param publicKey the public key
     * @return the response
     */
    @Path("publicKey")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response generatePublicKey(String publicKey) {
        try {

            byte[] keyBytes = publicKey.getBytes();
            Cipher c = Cipher.getInstance("RSA");
            PublicKey key = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(keyBytes));
            mobileCashAndroidPublicKey = key;
            return new PublicKeyResponse("");
        } catch (Exception e) {
            return new ErrorResponse("The publicKey was not saved.");
        }
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
        KeyPairGenerator.mobileCashAndroidPublicKey = mobileCashAndroidPublicKey;
    }
}
