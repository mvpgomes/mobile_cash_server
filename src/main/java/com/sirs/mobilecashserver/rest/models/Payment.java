package com.sirs.mobilecashserver.rest.models;

public class Payment {
	private String username;
	private String password;
	private String product;

	public Payment() {
	}

	public Payment(String username, String password, String product) {
		this.username = username;
		this.password = password;
		this.product = product;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

}
