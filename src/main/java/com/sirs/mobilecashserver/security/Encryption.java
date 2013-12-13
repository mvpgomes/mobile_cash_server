package com.sirs.mobilecashserver.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

/**
 * The Class Encryption.
 */
@SuppressWarnings("restriction")
public class Encryption {

    /** The Constant ALGO. */
    private static final String ALGO = "AES";

    /** The Constant keyValue. */
    private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K',
            'e', 'y' };

    /**
     * Encrypt.
     * 
     * @param Data the data
     * @param seed the seed
     * @return the string
     * @throws Exception the exception
     */
    public static String encrypt(String Data, byte[] seed) throws Exception {
        Key key = generateKey(seed);
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    /**
     * Generate key.
     * 
     * @param seed the seed
     * @return the key
     * @throws Exception the exception
     */
    private static Key generateKey(byte[] seed) throws Exception {
        byte[] newKey = new byte[keyValue.length + seed.length];
        System.arraycopy(keyValue, 0, newKey, 0, keyValue.length);
        System.arraycopy(seed, 0, newKey, keyValue.length, seed.length);
        Key key = new SecretKeySpec(newKey, ALGO);
        return key;
    }

}
