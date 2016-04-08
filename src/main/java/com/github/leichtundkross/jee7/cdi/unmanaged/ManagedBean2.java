package com.github.leichtundkross.jee7.cdi.unmanaged;

import java.util.logging.Logger;

public class ManagedBean2 {

	private static final Logger LOGGER = Logger.getLogger(ManagedBean2.class.getCanonicalName());

	void sayHello() {
		LOGGER.info("called ManagedBean2#sayHello()");
	}
}
