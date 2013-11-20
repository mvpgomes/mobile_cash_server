package com.sirs.mobilecashserver.db;

/**
 * The Class BankAccount.
 */
public class BankAccount {

	/** The username. */
	private String username;

	/** The balance. */
	private double balance;

	/**
	 * Instantiates a new bank account.
	 * 
	 * @param username
	 *            the username
	 * @param balance
	 *            the balance
	 */
	public BankAccount(String username, double balance) {
		this.username = username;
		this.balance = balance;
	}

	/**
	 * Gets the balance.
	 * 
	 * @return the balance
	 */
	double getBalance() {
		return this.balance;
	}

	/**
	 * Sets the balance.
	 * 
	 * @param balance
	 *            the new balance
	 */
	void setBalance(float balance) {
		this.balance = balance;
	}

	/**
	 * Gets the username.
	 * 
	 * @return the username
	 */
	String getUsername() {
		return this.username;
	}

	/**
	 * Sets the username.
	 * 
	 * @param username
	 *            the new username
	 */
	void setUsername(String username) {
		this.username = username;
	}

}
