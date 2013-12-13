package com.sirs.mobilecashserver.rest.models;

/**
 * The Class ErrorResponse.
 */
public class ErrorResponse extends Response {

    /** The message. */
    String message;

    /**
     * Instantiates a new error response.
     */
    public ErrorResponse() {
        super();
        setType("ERROR");
    }

    /**
     * Instantiates a new error response.
     * 
     * @param message the message
     */
    public ErrorResponse(String message) {
        super();
        this.message = message;
        setType("ERROR");
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
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
