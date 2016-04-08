package com.github.leichtundkross.jee7.cdi.unmanaged;

import javax.enterprise.inject.spi.Unmanaged;
import javax.enterprise.inject.spi.Unmanaged.UnmanagedInstance;

public class UnmanagedBean {

	void sayHello() {
		// create managed bean
		Unmanaged<ManagedBean1> unmanagedFoo = new Unmanaged<>(ManagedBean1.class);
		UnmanagedInstance<ManagedBean1> managedBean = unmanagedFoo.newInstance();
		ManagedBean1 bean = managedBean.produce().inject().postConstruct().get();

		// use bean
		bean.sayHello();

		// destroy bean
		managedBean.preDestroy().dispose();
	}
}
