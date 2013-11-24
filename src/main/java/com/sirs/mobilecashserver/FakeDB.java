package com.sirs.mobilecashserver;

import com.sirs.mobilecashserver.db.MobileCashServerDB;

/**
 * * The Class Populate.
 */
public class Populate {

	/** The USERNAM e_1. */
	private static String USERNAME_1 = "mvpgomes";

	/** The USERNAM e_2. */
	private static String USERNAME_2 = "guilhermeferreira";

	/** The USERNAM e_3. */
	private static String USERNAME_3 = "samuelcoelho";

	/** The PASSWOR d_1. */
	private static String PASSWORD_1 = "ist169376";

	/** The PASSWOR d_2. */
	private static String PASSWORD_2 = "ist169316";

	/** The PASSWOR d_3. */
	private static String PASSWORD_3 = "ist169350";

	/** The BALANC e_1. */
	private static double BALANCE_1 = 250.00;

	/** The BALANC e_2. */
	private static double BALANCE_2 = 250.00;

	/** The BALANC e_3. */
	private static double BALANCE_3 = 385.00;

	/**
	 * Instantiates a new populate.
	 */
	public Populate() {

	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String args[]) {

		MobileCashServerDB database = MobileCashServerDB.getInstance();

		database.registerUser(USERNAME_1, PASSWORD_1);
		database.registerUser(USERNAME_2, PASSWORD_2);
		database.registerUser(USERNAME_3, PASSWORD_3);
		database.registerAccount(USERNAME_1, BALANCE_1);
		database.registerAccount(USERNAME_2, BALANCE_2);
		database.registerAccount(USERNAME_3, BALANCE_3);

	}
}
