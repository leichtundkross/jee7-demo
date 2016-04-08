package com.github.leichtundkross.jee7.jms.jms20;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.Queue;

public class JMS2Receiver {

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/jms/queue/SampleQueue1.1")
	private Queue queue;

	public String receiveMessage() {
		String body = null;
		try (JMSContext context = connectionFactory.createContext();) {
			// connection.start() doesn't need to be called anymore
			// no need for casts and XXXMessage objects anymore
			JMSConsumer consumer = context.createConsumer(queue);
			body = consumer.receiveBody(String.class);
		} catch (JMSRuntimeException ex) {
			// handle exception (details omitted)
		}

		return body;
	}
}
