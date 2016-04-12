package com.github.leichtundkross.jee7.jsonp;

import java.io.StringReader;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.json.stream.JsonParserFactory;

class JsonParserDemo {

	@SuppressWarnings("incomplete-switch")
	void parseJson(String json) {
		JsonParserFactory factory = Json.createParserFactory(null);
		JsonParser parser = factory.createParser(new StringReader(json));

		while (parser.hasNext()) {
			Event event = parser.next();

			switch (event) {
			case KEY_NAME: {
				System.out.print(parser.getString() + "=");
				break;
			}
			case VALUE_STRING: {
				System.out.println(parser.getString());
				break;
			}
			}
		}
	}
}
