package com.github.leichtundkross.jee7.jsonp;

import static org.junit.Assert.assertEquals;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonStructure;
import javax.json.JsonValue.ValueType;

import org.junit.Test;

public class JsonReaderDemoTest {

	@Test
	public void readJson() {
		String json = "{\"firstName\":\"John\",\"lastName\":\"Smith\",\"age\":25," //
				+ "\"phoneNumber\":[{\"type\":\"office\",\"number\":\"233 555-5678\"}]}";

		JsonStructure jsonStructure = new JsonReaderDemo().readJson(json);

		assertEquals(ValueType.OBJECT, jsonStructure.getValueType());
		assertEquals("Smith", ((JsonObject) jsonStructure).getString("lastName"));
		assertEquals("{\"type\":\"office\",\"number\":\"233 555-5678\"}", ((JsonArray) ((JsonObject) jsonStructure).get("phoneNumber")).get(0).toString());
	}
}
