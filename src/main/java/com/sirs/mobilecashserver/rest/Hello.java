package com.sirs.mobilecashserver.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * The Class Hello.
 */
@Path("/hello")
public class Hello {

    /**
     * Gets the msg.
     * 
     * @return the msg
     */
    @GET
    public Response getMsg() {

        DateTime dt = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        String output = "Jersey say : Hello !!! ";

        output += "at " + fmt.print(dt) + " timestamp " + dt.getMillis();

        return Response.status(200).entity(output).build();
    }
}
