package com.github.leichtundkross.jee7.jsonp;

import java.io.Writer;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

class JsonWriterDemo {

	void writeJson(Writer writer) {
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObject value = factory.createObjectBuilder() //
				.add("firstName", "John") //
				.add("lastName", "Smith") //
				.add("age", 25) //
				.add("phoneNumber",
						factory.createArrayBuilder() //
								.add(factory.createObjectBuilder() //
										.add("type", "office") //
										.add("number", "233 555-5678") //
						)) //
				.build();

		Json.createWriter(writer).write(value);
	}
}
