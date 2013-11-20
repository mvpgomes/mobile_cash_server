package com.sirs.mobilecashserver.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sirs.mobilecashserver.rest.models.Payment;

@Path("api")
public class BuyService {

	@POST
	// @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String buy(Payment payment) {
		return "ok";
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Payment buy() {
		return new Payment("test", "test", "test");
	}
}
