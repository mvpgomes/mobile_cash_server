package com.sirs.mobilecashserver.rest.models;

/**
 * The Class User.
 */
public class User {

    /** The username. */
    private String username;

    /** The password. */
    private byte[] password;

    /** The salt. */
    private String salt;

    /**
     * Gets the salt.
     * 
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Sets the salt.
     * 
     * @param salt the new salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Instantiates a new user.
     * 
     * @param username the username
     * @param password the password
     * @param salt the salt
     */
    public User(String username, byte[] password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
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
     * @param password the new password
     */
    public void setPassword(byte[] password) {
        this.password = password;
    }

}
