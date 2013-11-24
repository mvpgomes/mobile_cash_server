package com.sirs.mobilecashserver.rest.models;

public class PaymentResponse extends Response {
	private String username;
	private String product;
	private double balance;

	public PaymentResponse() {
		this.username = "";
		this.product = "";
		this.balance = 0;
	}

	public PaymentResponse(String username, String product, double balance) {
		super();
		this.username = username;
		this.product = product;
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
