package com.github.leichtundkross.jee7.cdi.interceptor;

import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.interceptor.AroundConstruct;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Interceptor priority must be bewteen {@link Interceptor.Priority#APPLICATION} (2000) and {@link Interceptor.Priority#LIBRARY_AFTER}
 * (3000). Adding Priority annotation enables a interceptor across multiple modules within the same archive
 */
@Priority(Interceptor.Priority.APPLICATION + 100)
@Interceptor
public class VeryImportantInterceptor {

	private static final Logger LOGGER = Logger.getLogger(VeryImportantInterceptor.class.getCanonicalName());

	@AroundConstruct
	public void interceptClassConstruction(InvocationContext ctx) throws Exception {
		Class<? extends InvocationContext> classToConstruct = ctx.getClass();
		LOGGER.info("Intecpt constrcution of new bean " + classToConstruct.getSimpleName());
		ctx.proceed();
	}
}
