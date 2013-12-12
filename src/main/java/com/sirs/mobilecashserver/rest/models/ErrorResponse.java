package com.sirs.mobilecashserver.rest.models;

public class ErrorResponse extends Response {
	String message;

	public ErrorResponse() {
		super();
		setType("ERROR");
	}

	public ErrorResponse(String message) {
		super();
		this.message = message;
		setType("ERROR");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
