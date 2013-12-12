package com.sirs.mobilecashserver.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Encryption {

	private static final String ALGO = "AES";
	private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B',
			'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

	public static String encrypt(String Data, byte[] seed) throws Exception {
		Key key = generateKey(seed);
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = new BASE64Encoder().encode(encVal);
		return encryptedValue;
	}

	private static Key generateKey(byte[] seed) throws Exception {
		byte[] newKey = new byte[keyValue.length + seed.length];
		System.out.println("Key length " + keyValue.length + " Seed length "
				+ seed.length);
		System.arraycopy(keyValue, 0, newKey, 0, keyValue.length);
		System.arraycopy(seed, 0, newKey, keyValue.length, seed.length);
		Key key = new SecretKeySpec(newKey, ALGO);
		System.out.println("Sending key " + new String(newKey));
		return key;
	}

}
