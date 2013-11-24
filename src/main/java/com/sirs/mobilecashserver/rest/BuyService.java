package com.sirs.mobilecashserver.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sirs.mobilecashserver.db.MobileCashServerDB;
import com.sirs.mobilecashserver.rest.models.BankAccount;
import com.sirs.mobilecashserver.rest.models.ErrorResponse;
import com.sirs.mobilecashserver.rest.models.Payment;
import com.sirs.mobilecashserver.rest.models.PaymentResponse;
import com.sirs.mobilecashserver.rest.models.Product;
import com.sirs.mobilecashserver.rest.models.Response;
import com.sirs.mobilecashserver.rest.models.User;

@Path("buy")
public class BuyService {

	private final MobileCashServerDB db = MobileCashServerDB.getInstance();

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response buy(Payment payment) {
		User user = db.login(payment.getUsername(), payment.getPassword());

		if (user != null) {
			Product product = db.getProduct(payment.getProduct());
			BankAccount account = db.getAccount(payment.getUsername());

			if (product == null) {
				return new ErrorResponse("Product " + payment.getProduct()
						+ " does not exist");
			}

			// Check if the user can buy this product
			if (account.getBalance() >= product.getPrice()) {
				double oldBalance = account.getBalance();
				double newBalance = oldBalance - product.getPrice();
				account.setBalance(newBalance);
				return new PaymentResponse(user.getUsername(),
						product.getCode(), account.getBalance());
			}
			return new ErrorResponse("Insufficient balance");

		}
		return new ErrorResponse("Wrong username/password");

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Payment buy() {
		return new Payment("test", "test", "test");
	}
}
