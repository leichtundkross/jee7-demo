package com.github.leichtundkross.jee7.jms.jms20;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSRuntimeException;
import javax.jms.Queue;

@Singleton
@Startup
public class JMS2Sender {

	private static final Logger LOGGER = Logger.getLogger(JMS2Sender.class.getCanonicalName());

	@Inject
	// optional since we have only one JMS ConnectionFactory
	// @JMSConnectionFactory("java:/ConnectionFactory")
	private JMSContext context;

	@Resource(lookup = "java:/jms/queue/SampleQueue2.0")
	private Queue queue;

	public void sendMessage(String text) {
		LOGGER.info("Sending message to queue '" + getQueueName() + "':" + text);
		context.createProducer().send(queue, text);
	}

	public void sendMessage(String text, long deliveryDelay) {
		LOGGER.info("Sending message to queue '" + getQueueName() + "':" + text);
		context.createProducer() //
				.setDeliveryDelay(deliveryDelay) //
				.send(queue, text);
	}

	@Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
	public void sendJMSMessageEvery10Secs() {
		String text = "Message from " + LocalDateTime.now();
		sendMessage(text, 5000l);
	}

	private String getQueueName() {
		try {
			return queue.getQueueName();
		} catch (JMSException e) {
			throw new JMSRuntimeException(e.getMessage());
		}
	}
}
