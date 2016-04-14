package com.github.leichtundkross.jee7.cdi.unmanaged;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

public class ManagedBean1 {

	private static final Logger LOGGER = Logger.getLogger(ManagedBean1.class.getCanonicalName());

	@Inject
	private ManagedBean2 bean2;

	@PostConstruct
	public void init() {
		LOGGER.info("init ManagedBean1...");
	}

	void sayHello() {
		bean2.sayHello();
	}
}
