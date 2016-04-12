package com.github.leichtundkross.jee7.websocket;

import java.io.IOException;
import java.util.logging.Logger;

import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatEndpoint {

	private static final Logger LOGGER = Logger.getLogger(ChatEndpoint.class.getCanonicalName());

	@OnMessage
	public void message(String message, Session client) throws IOException, EncodeException {
		LOGGER.info("Server received message: " + message);
		for (Session peer : client.getOpenSessions()) {
			peer.getBasicRemote().sendText(message);
		}
	}
}