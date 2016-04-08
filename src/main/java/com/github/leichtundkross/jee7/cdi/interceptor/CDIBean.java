package com.github.leichtundkross.jee7.cdi.interceptor;

import java.util.logging.Logger;

import javax.interceptor.Interceptors;

/**
 * This is a simple CDI bean (POJO).
 */
// the order defined in @Interceptors annotation also defines the invocation order (regardless a @Priority annotation)
@Interceptors({ CDI11Interceptor.class, VeryImportantInterceptor.class })
public class CDIBean {

	private static final Logger LOGGER = Logger.getLogger(CDIBean.class.getCanonicalName());

	public CDIBean() {
		LOGGER.info("Construct new CDIBean...");
	}
}
