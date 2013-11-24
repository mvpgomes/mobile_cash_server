package com.sirs.mobilecashserver.rest.models;

public class Product {
	private String code;
	private double price;
	
	public Product() {
		super();
		this.code = "";
		this.price = 0;
	}
	
	public Product(String code, double price) {
		super();
		this.code = code;
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
}
