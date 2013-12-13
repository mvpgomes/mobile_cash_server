package com.sirs.mobilecashserver;

import java.security.NoSuchAlgorithmException;

import com.sirs.mobilecashserver.db.MobileCashServerDB;

/**
 * The Class FakeDB.
 */
public class FakeDB {

    /** The USERNAM e_1. */
    private static String USERNAME_1 = "mvpgomes";

    /** The USERNAM e_2. */
    private static String USERNAME_2 = "gf";

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

    /** The PRODUC t_1. */
    private static String PRODUCT_1 = "coca-cola";

    /** The PRIC e_1. */
    private static double PRICE_1 = 0.75;

    /** The PRODUC t_2. */
    private static String PRODUCT_2 = "durex-sensitive";

    /** The PRIC e_2. */
    private static double PRICE_2 = 8.00;

    /**
     * Instantiates a new fake db.
     */
    public FakeDB() {

    }

    /**
     * Inits the.
     * 
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
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
