package de.wennersonline.jms.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

public class Sender {
	
	private static final Logger logger = LoggerFactory.getLogger(Sender.class);
	
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void send(String destination, String message) {
		logger.info("sending message='{}' to destination='{}'", message, destination);
		jmsTemplate.convertAndSend(destination, message);
	}
	

}
