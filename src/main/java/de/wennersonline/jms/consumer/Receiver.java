package de.wennersonline.jms.consumer;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

public class Receiver {

	private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	@JmsListener(destination = "${activemq.queue.helloworld}")
	public void receive(String message) {
		logger.info("received message='{}'", message);
		latch.countDown();
	}

}
