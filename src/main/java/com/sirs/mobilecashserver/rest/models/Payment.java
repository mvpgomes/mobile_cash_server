package com.sirs.mobilecashserver.rest.models;

/**
 * The Class Payment.
 */
public class Payment {

    /** The username. */
    private String username;

    /** The password. */
    private String password;

    /** The product. */
    private String product;

    /** The message's digest. */
    private byte[] digest;

    /**
     * Instantiates a new payment.
     */
    public Payment() {
    }

    /**
     * Instantiates a new payment.
     * 
     * @param username the username
     * @param password the password
     * @param product the product
     * @param digest the digest
     */
    public Payment(String username, String password, String product, byte[] digest) {
        this.username = username;
        this.password = password;
        this.product = product;
        this.digest = digest;
    }

    /**
     * Gets the username.
     * 
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * 
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * 
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the product.
     * 
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * Gets the digest.
     * 
     * @return the digest
     */
    public byte[] getDigest() {
        return digest;
    }

    /**
     * Sets the digest.
     * 
     * @param digest the new digest
     */
    public void setDigest(byte[] digest) {
        this.digest = digest;
    }

    /**
     * Sets the product.
     * 
     * @param product the new product
     */
    public void setProduct(String product) {
        this.product = product;
    }

}
