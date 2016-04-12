package com.github.leichtundkross.jee7.jsonp;

import java.io.Writer;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

/**
 * A {@link JsonGenerator} prints the JSON directly into a stream (f.e. a file on disk) without generating a object model in memory.
 */
class JsonGeneratorDemo {

	void generateJson(Writer writer) {
		JsonGeneratorFactory factory = Json.createGeneratorFactory(null);
		JsonGenerator generator = factory.createGenerator(writer);

		generator.writeStartArray() //
				.writeStartObject() //
					.write("type", "home") //
					.write("number", "(800) 111-1111").writeEnd() //
				.writeStartObject() //
					.write("type", "cell") //
					.write("number", "(800) 222-2222").writeEnd() //
				.writeEnd() //
			.close();
	}
}
