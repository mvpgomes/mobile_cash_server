package com.sirs.mobilecashserver.rest.models;

public class User {

	private String username;
	private byte[] password;
	private String salt;

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public User(String username, byte[] password, String salt) {
		this.username = username;
		this.password = password;
		this.salt = salt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

}
