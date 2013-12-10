package com.sirs.mobilecashserver.rest.models;

/**
 * The Class User.
 */
public class User {

	/** The username. */
	private String username;

	/** The password. */
	private byte[] password;

	/**
	 * Instantiates a new user.
	 * 
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 */
	public User(String username, byte[] password) {
		this.username = username;
		this.password = password;
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
	public byte[] getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the new password
	 */
	public void setPassword(byte[] password) {
		this.password = password;
	}

}
