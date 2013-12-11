package com.sirs.mobilecashserver.db;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.sirs.mobilecashserver.rest.models.BankAccount;
import com.sirs.mobilecashserver.rest.models.Product;
import com.sirs.mobilecashserver.rest.models.User;

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

	private final Map<String, Product> products;

	/**
	 * Instantiates a new mobile cash server db.
	 */
	public MobileCashServerDB() {
		this.users = new HashMap<String, User>();
		this.accounts = new HashMap<String, BankAccount>();
		this.products = new HashMap<String, Product>();
	}

	/**
	 * Register user.
	 * 
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @throws NoSuchAlgorithmException
	 */
	public void registerUser(String username, String password)
			throws NoSuchAlgorithmException {
		String salt = UUID.randomUUID().toString();
		User user = new User(username, hash(password + salt), salt);
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

	public void registerProduct(String code, double price) {
		Product product = new Product(code, price);
		this.products.put(code, product);
	}

	public Product getProduct(String code) {
		return this.products.get(code);
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

	public User login(String username, String password) {
		User user = getUser(username);
		String salt = "";
		try {
			if (user != null)
				salt = user.getSalt();
			byte[] hashedPassword = hash(password + salt);

			if (Arrays.equals(user.getPassword(), hashedPassword)) {
				return user;
			} else {
				return null;
			}
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	private byte[] hash(String stringToHash) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");

		// Hash the message
		digest.update(stringToHash.getBytes());
		// String hash = new String(digest.digest());
		byte[] hash = digest.digest();

		return hash;
	}
}
