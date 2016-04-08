package com.github.leichtundkross.jee7.jms.jms11;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JMS11Receiver {

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/jms/queue/SampleQueue1.1")
	private Queue queue;

	public String receiveMessage() {
		String body = null;
		try {
			Connection connection = connectionFactory.createConnection();
			try {
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				MessageConsumer messageConsumer = session.createConsumer(queue);
				connection.start();
				TextMessage textMessage = (TextMessage) messageConsumer.receive();
				body = textMessage.getText();
			} finally {
				connection.close();
			}
		} catch (JMSException ex) {
			// handle exception (details omitted)
		}

		return body;
	}
}
