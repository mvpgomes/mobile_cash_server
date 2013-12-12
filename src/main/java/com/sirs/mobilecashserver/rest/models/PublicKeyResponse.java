package com.sirs.mobilecashserver.rest.models;

/**
 * The Class AvailabilityResponse.
 */
public class PublicKeyResponse extends Response {

	/** The message. */
	String message;

	/**
	 * Instantiates a new availability response.
	 */
	public PublicKeyResponse() {
		super();
		setType("PUBLIC-KEY");
	}

	/**
	 * Instantiates a new availability response.
	 * 
	 * @param message
	 *            the message
	 */
	public PublicKeyResponse(String message) {
		super();
		this.message = message;
		setType("PUBLIC-KEY");
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 * 
	 * @param message
	 *            the new message
	 */
	void setMessage(String message) {
		this.message = message;
	}

}
