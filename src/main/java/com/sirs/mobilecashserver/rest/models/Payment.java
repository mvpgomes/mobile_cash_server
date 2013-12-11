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

	private long timestamp;

	/** The message's digest. */
	private String hash;

	/**
	 * Instantiates a new payment.
	 */
	public Payment() {
	}

	/**
	 * Instantiates a new payment.
	 * 
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @param product
	 *            the product
	 * @param hash
	 *            the digest
	 */
	public Payment(String username, String password, String product,
			long timestamp, String hash) {
		this.username = username;
		this.password = password;
		this.product = product;
		this.timestamp = timestamp;
		this.hash = hash;
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
	 * @param username
	 *            the new username
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
	 * @param password
	 *            the new password
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
	public String getHash() {
		return hash;
	}

	/**
	 * Sets the digest.
	 * 
	 * @param hash
	 *            the new digest
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * Sets the product.
	 * 
	 * @param product
	 *            the new product
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
