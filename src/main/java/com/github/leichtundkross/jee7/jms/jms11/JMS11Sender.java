package com.github.leichtundkross.jee7.jms.jms11;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.JMSRuntimeException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@Singleton
@Startup
public class JMS11Sender {

	private static final Logger LOGGER = Logger.getLogger(JMS11Sender.class.getCanonicalName());

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/jms/queue/SampleQueue1.1")
	private Queue queue;

	public void sendMessage(String text) {
		LOGGER.info("Sending message to queue '" + getQueueName() + "':" + text);

		try {
			Connection connection = connectionFactory.createConnection();
			try {
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				MessageProducer messageProducer = session.createProducer(queue);
				TextMessage textMessage = session.createTextMessage(text);
				messageProducer.send(textMessage);
			} finally {
				connection.close();
			}
		} catch (JMSException ex) {
			// handle exception (details omitted)
		}
	}

	// disabled since we do not have an receiver
	// @Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
	public void sendJMSMessageEvery10Secs() {
		String text = "Message from " + LocalDateTime.now();
		sendMessage(text);
	}

	private String getQueueName() {
		try {
			return queue.getQueueName();
		} catch (JMSException e) {
			throw new JMSRuntimeException(e.getMessage());
		}
	}
}
