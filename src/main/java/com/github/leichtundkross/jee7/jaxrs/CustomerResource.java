package com.github.leichtundkross.jee7.jaxrs;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

// must be a EJB since we use @Asynchronous
@Stateless
@Path("customer")
public class CustomerResource {

	@Context
	private UriInfo uriInfo;

	@GET
	@Path("{name}")
	@Produces("application/json")
	public Customer getCustomer(@PathParam("name") String name) {
		return new Customer(name);
	}

	@GET
	@Path("hateoas/{name}")
	@Produces("application/json")
	public Response getCustomerHateoas(@PathParam("name") String name) {
		return Response.ok() //
				.entity(new Customer(name)) //
				.links(Link.fromUri(uriInfo.getAbsolutePath()).rel("self").type("GET").build(), //
						Link.fromUri(uriInfo.getAbsolutePath() + "/invoice").rel("invoice").type("GET").build()) //
				.build();
	}

	// 1 JAX-RS runtime dispatches a thread to accept a connection
	// 2 The connection is accepted and is handed over to an EJB worker thread for background processing
	// JAX-RS runtime releases the acceptor thread and returns it to the pool. It can then use it to accept more connections
	@GET
	@Path("all")
	@Asynchronous
	@Produces("application/json")
	public void asyncRestMethod(@Suspended final AsyncResponse asyncResponse) {
		asyncResponse.setTimeout(40, TimeUnit.SECONDS);
		asyncResponse.setTimeoutHandler(response -> response.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("TIME OUT!").build()));

		List<Customer> allCustomers = Arrays.asList(new Customer("Google"), new Customer("Facebook"));
		asyncResponse.resume(allCustomers);
	}
}
