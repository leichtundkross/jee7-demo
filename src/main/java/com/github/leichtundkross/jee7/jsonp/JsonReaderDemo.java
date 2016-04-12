package com.github.leichtundkross.jee7.jsonp;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;

class JsonReaderDemo {

	JsonStructure readJson(String json) {
		try (JsonReader jsonReader = Json.createReader(new StringReader(json))) {
			return jsonReader.read();
		}
	}
}
