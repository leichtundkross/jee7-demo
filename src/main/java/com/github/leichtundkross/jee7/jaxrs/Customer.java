package com.github.leichtundkross.jee7.jaxrs;

public class Customer {

	private String name;

	public Customer() {
		// for serialization
	}

	Customer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + "]";
	}
}
