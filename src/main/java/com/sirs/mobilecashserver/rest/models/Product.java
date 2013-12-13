package com.sirs.mobilecashserver.rest.models;

/**
 * The Class Product.
 */
public class Product {

    /** The code. */
    private String code;

    /** The price. */
    private double price;

    /**
     * Instantiates a new product.
     */
    public Product() {
        super();
        this.code = "";
        this.price = 0;
    }

    /**
     * Instantiates a new product.
     * 
     * @param code the code
     * @param price the price
     */
    public Product(String code, double price) {
        super();
        this.code = code;
        this.price = price;
    }

    /**
     * Gets the code.
     * 
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     * 
     * @param code the new code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the price.
     * 
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price.
     * 
     * @param price the new price
     */
    public void setPrice(double price) {
        this.price = price;
    }

}
