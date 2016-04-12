package com.github.leichtundkross.jee7.jsonp;

import org.junit.Test;

public class JsonParserDemoTest {

	@Test
	public void parseJson() {
		String json = "[" //
				+ "{\"type\" : \"home\", \"number\" : \"(800) 111-1111\"}," //
				+ "{\"type\" : \"cell\", \"number\" : \"(800) 222-2222\"}]";

		new JsonParserDemo().parseJson(json);

		// this is not a test :-)
		// watch the console
	}
}
