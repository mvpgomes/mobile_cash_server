package com.sirs.mobilecashserver.rest;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sirs.mobilecashserver.rest.models.ErrorResponse;
import com.sirs.mobilecashserver.rest.models.Key;
import com.sirs.mobilecashserver.rest.models.PublicKeyResponse;
import com.sirs.mobilecashserver.rest.models.Response;
import com.sirs.mobilecashserver.security.DigitalSignatureManager;
import com.sun.jersey.core.util.Base64;

/**
 * The Class KeyPairGenerator.
 */
@Path("publicKey")
public class GetClientPublicKey {

    /**
     * Generate public key.
     * 
     * @param publicKey the public key
     * @return the response
     */
    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response generatePublicKey(Key publicKey) {
        try {
            byte[] data = Base64.decode(publicKey.getPublicKey());
            X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PublicKey key = fact.generatePublic(spec);
            DigitalSignatureManager.setMobileCashAndroidPublicKey(key);
            return new PublicKeyResponse("The public key was saved with success.");
        } catch (Exception e) {
            return new ErrorResponse("The publicKey was not saved." + e.getMessage());
        }
    }
}
