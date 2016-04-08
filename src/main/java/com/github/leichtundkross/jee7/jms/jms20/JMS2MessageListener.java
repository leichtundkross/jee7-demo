package com.github.leichtundkross.jee7.jms.jms20;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.JMSRuntimeException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * JEE standard compliant ActivationConfigProperties:
 * <ul>
 * <li>connectionFactoryLookup</li>
 * <li>destinationLookup</li>
 * <li>destinationType</li>
 * <li>acknowledgeMode</li>
 * <li>messageSelector</li>
 * <li>subscriptionDurability</li>
 * <li>clientId</li>
 * <li>subscriptionName</li>
 * </ul>
 */
@MessageDriven(activationConfig = {
		// optional since we have only one JMS ConnectionFactory
		// @ActivationConfigProperty(propertyName = "connectionFactoryLookup", propertyValue = "java:/ConnectionFactory"),
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/SampleQueue2.0"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class JMS2MessageListener implements MessageListener {

	private static final Logger LOGGER = Logger.getLogger(JMS2MessageListener.class.getCanonicalName());

	@Override
	public void onMessage(Message message) {
		try {
			checkMessageType(message, String.class);
			checkNumberOfDeliveries(message);
			String text = message.getBody(String.class);
			LOGGER.info("Received message :" + text);
		} catch (JMSException e) {
			throw new JMSRuntimeException(e.getMessage());
		}
	}

	private void checkMessageType(Message message, Class<?> expectedType) throws JMSException {
		// we can now check message type without using instanceof
		if (!message.isBodyAssignableTo(expectedType)) {
			throw new IllegalArgumentException();
		}
	}

	private void checkNumberOfDeliveries(Message message) throws JMSException {
		final int maxRetries = 10;
		int deliveryCount = message.getIntProperty("JMSXDeliveryCount");
		if (deliveryCount > maxRetries) {
			// message has been redelivered ten times,
			// let's do something to prevent endless redeliveries
			// - such as sending it to dead message queue
		}
	}
}
