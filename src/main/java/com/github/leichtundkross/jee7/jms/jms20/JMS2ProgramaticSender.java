package com.github.leichtundkross.jee7.jms.jms20;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.Queue;

public class JMS2ProgramaticSender {

	public void sendMessage(ConnectionFactory connectionFactory, Queue queue, String text) {
		// JMSContext replaces Connection and Session objects
		// support try-with-resources (AutoCloseable)
		// Session.AUTO_ACKNOWLEDGE is default
		try (JMSContext context = connectionFactory.createContext();) {
			// TextMessage is created based on passed argument
			context.createProducer().send(queue, text);
		} catch (JMSRuntimeException ex) {
			// this is a RuntimeException - we do not need to handle it
			// handle exception (details omitted)
		}
	}
}
