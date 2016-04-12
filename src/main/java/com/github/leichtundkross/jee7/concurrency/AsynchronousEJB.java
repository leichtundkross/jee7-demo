package com.github.leichtundkross.jee7.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;

@Stateless
public class AsynchronousEJB {

	private static final Logger LOGGER = Logger.getLogger(AsynchronousEJB.class.getCanonicalName());

	@Resource
	private ManagedExecutorService executor;

	public void synchronousMethod() {
		Future<?> result = executor.submit(() -> LOGGER.info("I am running asynchronous with annotation :-)"));

		// wait for the result if necessary
		try {
			result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
