package com.sirs.mobilecashserver.rest.models;

/**
 * The Class Key.
 */
public class Key {

    /** The public key. */
    String publicKey;

    /**
     * Instantiates a new key.
     */
    public Key() {
    }

    /**
     * Instantiates a new key.
     * 
     * @param key the key
     */
    public Key(String key) {
        this.publicKey = key;
    }

    /**
     * Gets the public key.
     * 
     * @return the public key
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * Sets the public key.
     * 
     * @param publicKey the new public key
     */
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

}
