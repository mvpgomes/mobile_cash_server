package com.sirs.mobilecashserver.db;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class MobileCashServerDB.
 */
public class MobileCashServerDB {

	/** The instance. */
	private static MobileCashServerDB instance;

	/** The users. */
	private final Map<String, User> users;

	/** The accounts. */
	private final Map<String, BankAccount> accounts;

	/**
	 * Instantiates a new mobile cash server db.
	 */
	public MobileCashServerDB() {
		this.users = new HashMap<String, User>();
		this.accounts = new HashMap<String, BankAccount>();
	}

	/**
	 * Register user.
	 * 
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 */
	public void registerUser(String username, String password) {
		User user = new User(username, password);
		this.users.put(username, user);
	}

	/**
	 * Gets the user.
	 * 
	 * @param username
	 *            the username
	 * @return the user
	 */
	public User getUser(String username) {
		return this.users.get(username);
	}

	/**
	 * Register account.
	 * 
	 * @param username
	 *            the username
	 * @param balance
	 *            the balance
	 */
	public void registerAccount(String username, double balance) {
		BankAccount account = new BankAccount(username, balance);
		this.accounts.put(username, account);
	}

	/**
	 * Gets the account.
	 * 
	 * @param username
	 *            the username
	 * @return the account
	 */
	public BankAccount getAccount(String username) {
		return this.accounts.get(username);
	}

	/**
	 * Gets the single instance of MobileCashServerDB.
	 * 
	 * @return single instance of MobileCashServerDB
	 */
	public static MobileCashServerDB getInstance() {
		if (MobileCashServerDB.instance == null) {
			MobileCashServerDB.instance = new MobileCashServerDB();
		}
		return MobileCashServerDB.instance;
	}
}
