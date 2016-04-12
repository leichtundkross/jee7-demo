package com.github.leichtundkross.jee7.websocket;

import java.io.IOException;
import java.util.logging.Logger;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class ChatClientEndpoint {

	private static final Logger LOGGER = Logger.getLogger(ChatClientEndpoint.class.getCanonicalName());

	private static String TEXT = "Java Client joined";

	@OnOpen
	public void onOpen(Session session) {
		try {
			session.getBasicRemote().sendText(TEXT);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@OnMessage
	public void processMessage(String message) {
		LOGGER.info("Java Client received message: " + message);
	}
}
