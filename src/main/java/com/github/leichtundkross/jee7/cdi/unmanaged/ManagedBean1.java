package com.github.leichtundkross.jee7.cdi.unmanaged;

import javax.inject.Inject;

public class ManagedBean1 {

	@Inject
	private ManagedBean2 bean2;

	void sayHello() {
		bean2.sayHello();
	}
}
