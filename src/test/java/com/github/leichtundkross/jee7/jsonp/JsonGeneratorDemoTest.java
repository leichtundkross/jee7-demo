package com.github.leichtundkross.jee7.jsonp;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

public class JsonGeneratorDemoTest {

	@Test
	public void generateJson() {
		String exptectedJson = "[" //
				+ "{\"type\":\"home\",\"number\":\"(800) 111-1111\"}," //
				+ "{\"type\":\"cell\",\"number\":\"(800) 222-2222\"}]";

		Writer writer = new StringWriter();
		new JsonGeneratorDemo().generateJson(writer);

		assertEquals(exptectedJson, writer.toString());
	}
}
