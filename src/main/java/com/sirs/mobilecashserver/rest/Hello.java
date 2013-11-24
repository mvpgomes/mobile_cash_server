package com.sirs.mobilecashserver.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Hello world!
 * 
 */
@Path("/hello")
public class Hello {
	@GET
	public Response getMsg() {

		String output = "Jersey say : Hello !!!";

		return Response.status(200).entity(output).build();
	}

}
