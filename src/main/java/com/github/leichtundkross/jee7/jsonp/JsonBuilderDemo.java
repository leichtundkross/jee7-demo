package com.github.leichtundkross.jee7.jsonp;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * A {@link JsonObjectBuilder} generates an object model in memory that can be modified or printed as JSON string.
 */
class JsonBuilderDemo {

	String buildJson() {
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObject value = factory.createObjectBuilder() //
				.add("firstName", "John") //
				.add("lastName", "Smith") //
				.add("age", 25) //
				.add("address",
						factory.createObjectBuilder() //
								.add("streetAddress", "21 2nd Street") //
								.add("city", "New York") //
								.add("state", "NY") //
								.add("postalCode", "10021") //
				) //
				.add("phoneNumber",
						factory.createArrayBuilder() //
								.add(factory.createObjectBuilder() //
										.add("type", "home") //
										.add("number", "212 555-1234") //
								).add(factory.createObjectBuilder() //
										.add("type", "office") //
										.add("number", "233 555-5678") //
						)) //
				.build();

		return value.toString();
	}
}
