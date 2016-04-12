package com.github.leichtundkross.jee7.jsonp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JsonBuilderDemoTest {

	@Test
	public void buildJson() {
		String expectedJson = "{\"firstName\":\"John\",\"lastName\":\"Smith\",\"age\":25," //
				+ "\"address\":{\"streetAddress\":\"21 2nd Street\",\"city\":\"New York\",\"state\":\"NY\",\"postalCode\":\"10021\"}," //
				+ "\"phoneNumber\":[{\"type\":\"home\",\"number\":\"212 555-1234\"}," //
				+ "{\"type\":\"office\",\"number\":\"233 555-5678\"}]}";

		String generatedJson = new JsonBuilderDemo().buildJson();

		assertEquals(expectedJson, generatedJson);
	}
}
