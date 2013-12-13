package com.sirs.mobilecashserver.rest.models;

/**
 * The Class Response.
 */
public abstract class Response {

    /** The type. */
    String type;

    /**
     * Gets the type.
     * 
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     * 
     * @param type the new type
     */
    public void setType(String type) {
        this.type = type;
    }

}
