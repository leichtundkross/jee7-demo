package com.github.leichtundkross.jee7.cdi.interceptor;

import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.interceptor.AroundConstruct;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Priority(Interceptor.Priority.APPLICATION + 200)
@Interceptor
public class CDI11Interceptor {

	private static final Logger LOGGER = Logger.getLogger(CDI11Interceptor.class.getCanonicalName());

	@AroundConstruct
	public void interceptClassConstruction(InvocationContext ctx) throws Exception {
		Class<? extends InvocationContext> classToConstruct = ctx.getClass();
		LOGGER.info("Intercept constrcution of new bean " + classToConstruct.getSimpleName());
		ctx.proceed();
	}
}
