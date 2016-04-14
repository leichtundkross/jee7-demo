package com.github.leichtundkross.jee7.jaxrs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

// a PreMatching filter is executed before resource method is matched
// we could even modify URL or method
@Provider
@PreMatching
public class LoggingFilter implements ContainerRequestFilter {

	private static final Logger LOGGER = Logger.getLogger(LoggingFilter.class.getCanonicalName());

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		URI path = requestContext.getUriInfo().getAbsolutePath();
		LOGGER.info("Called " + requestContext.getMethod() + " " + path);

		if (path.toString().contains("rest/customers")) {
			try {
				LOGGER.info("Called deprecated URL 'customers'. Redirecting to new URL 'customer'");
				requestContext.setRequestUri(new URI(path.toString().replaceAll("customers", "customer")));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		} else if (!path.toString().contains("rest/customer")) {
			requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST).build());
		}
	}
}
