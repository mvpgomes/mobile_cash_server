package com.sirs.mobilecashserver;

import java.security.NoSuchAlgorithmException;

import com.sirs.mobilecashserver.db.MobileCashServerDB;

public class FakeDB {

	private static String USERNAME_1 = "mvpgomes";
	private static String USERNAME_2 = "gf";
	private static String USERNAME_3 = "samuelcoelho";

	private static String PASSWORD_1 = "ist169376";
	private static String PASSWORD_2 = "ist169316";
	private static String PASSWORD_3 = "ist169350";

	private static double BALANCE_1 = 250.00;
	private static double BALANCE_2 = 250.00;
	private static double BALANCE_3 = 385.00;

	private static String PRODUCT_1 = "coca-cola";
	private static double PRICE_1 = 0.75;

	private static String PRODUCT_2 = "durex-sensitive";
	private static double PRICE_2 = 8.00;

	public FakeDB() {

	}

	public static void init() throws NoSuchAlgorithmException {

		MobileCashServerDB database = MobileCashServerDB.getInstance();

		database.registerUser(USERNAME_1, PASSWORD_1);
		database.registerUser(USERNAME_2, PASSWORD_2);
		database.registerUser(USERNAME_3, PASSWORD_3);
		database.registerAccount(USERNAME_1, BALANCE_1);
		database.registerAccount(USERNAME_2, BALANCE_2);
		database.registerAccount(USERNAME_3, BALANCE_3);
		database.registerProduct(PRODUCT_1, PRICE_1);
		database.registerProduct(PRODUCT_2, PRICE_2);
	}
}
