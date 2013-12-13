package com.sirs.mobilecashserver.rest.models;

/**
 * The Class PaymentResponse.
 */
public class PaymentResponse extends Response {

    /** The username. */
    private String username;

    /** The product. */
    private String product;

    /** The balance. */
    private double balance;

    /**
     * Instantiates a new payment response.
     */
    public PaymentResponse() {
        this.username = "";
        this.product = "";
        this.balance = 0;
        setType("PAYMENT");
    }

    /**
     * Instantiates a new payment response.
     * 
     * @param username the username
     * @param product the product
     * @param balance the balance
     */
    public PaymentResponse(String username, String product, double balance) {
        super();
        this.username = username;
        this.product = product;
        this.balance = balance;
        setType("PAYMENT");
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
     * Gets the product.
     * 
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * Sets the product.
     * 
     * @param product the new product
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * Gets the balance.
     * 
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance.
     * 
     * @param balance the new balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
