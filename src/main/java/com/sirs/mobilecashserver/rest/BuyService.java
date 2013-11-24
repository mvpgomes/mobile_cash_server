package com.sirs.mobilecashserver.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sirs.mobilecashserver.db.MobileCashServerDB;
import com.sirs.mobilecashserver.rest.models.BankAccount;
import com.sirs.mobilecashserver.rest.models.Payment;
import com.sirs.mobilecashserver.rest.models.PaymentResponse;
import com.sirs.mobilecashserver.rest.models.Product;
import com.sirs.mobilecashserver.rest.models.User;

@Path("buy")
public class BuyService {

	private final MobileCashServerDB db = MobileCashServerDB.getInstance();

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public PaymentResponse buy(Payment payment) {
		User user = db.login(payment.getUsername(), payment.getPassword());

		if (user != null) {
			Product product = db.getProduct(payment.getProduct());
			BankAccount account = db.getAccount(payment.getUsername());
			// Check if the user can buy this product
			if (account.getBalance() >= product.getPrice()) {
				double oldBalance = account.getBalance();
				double newBalance = oldBalance - product.getPrice();
				account.setBalance(newBalance);
				return new PaymentResponse(user.getUsername(),
						product.getCode(), account.getBalance());
			}

		}

		return null;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Payment buy() {
		return new Payment("test", "test", "test");
	}
}
