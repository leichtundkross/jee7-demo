package com.github.leichtundkross.jee7.jsonp;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;

import org.junit.Test;

public class JsonWriterDemoTest {

	@Test
	public void writeJson() {
		String exptectedJson = "{\"firstName\":\"John\",\"lastName\":\"Smith\",\"age\":25," //
				+ "\"phoneNumber\":[{\"type\":\"office\",\"number\":\"233 555-5678\"}]}";

		StringWriter writer = new StringWriter();
		new JsonWriterDemo().writeJson(writer);

		assertEquals(exptectedJson, writer.toString());
	}
}
