package com.github.leichtundkross.jee7.jaxrs;

import java.util.logging.Logger;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

class CustomerClient {

	private static final Logger LOGGER = Logger.getLogger(CustomerClient.class.getCanonicalName());

	Customer getCustomer(String customerName) {
		LOGGER.info("Try to get customer for name " + customerName);

		try {
			Client client = ClientBuilder.newClient();
			Customer customer = client.target("http://localhost:8080/jee7-demo/rest") //
					.path("customer") //
					.path(customerName) //
					.request("application/json") //
					.get(Customer.class);

			LOGGER.info("Found customer: " + customer);
			return customer;
		} catch (NotFoundException e) {
			// handle HTTP 404 & co
			return null;
		}
	}
}
