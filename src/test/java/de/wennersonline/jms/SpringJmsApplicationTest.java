package de.wennersonline.jms;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.apache.activemq.broker.BrokerService;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import de.wennersonline.jms.consumer.Receiver;
import de.wennersonline.jms.producer.Sender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringJmsApplicationTest {

	private static ApplicationContext applicationContext;

	@Autowired
	void setContext(ApplicationContext applicationContext) {
		SpringJmsApplicationTest.applicationContext = applicationContext;
	}

	@AfterClass
	public static void afterClass() {
		((ConfigurableApplicationContext) applicationContext).close();
	}


	@Autowired
	private Sender sender;

	@Autowired
	private Receiver receiver;

	@Test
	public void testReceive() throws Exception {
		BrokerService brokerService = new BrokerService();
	    brokerService.addConnector("tcp://localhost:61616");
	    brokerService.setUseJmx(false);
	    brokerService.setPersistent(false);
	    brokerService.start();

		sender.send("helloworld.q", "Hello Spring JMS ActiveMQ!");
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
		
		sender.send("helloworld.q", "Hello Spring JMS ActiveMQ2!");
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
		
		sender.send("helloworld.q", "Hello Spring JMS ActiveMQ3!");
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
		
		sender.send("helloworld.q", "Hello Spring JMS ActiveMQ4!");
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
		
		sender.send("helloworld.q", "Hello Spring JMS ActiveMQ5!");
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
		
		sender.send("helloworld.q", "Hello Spring JMS ActiveMQ6!");
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
		
		//brokerService.stop();
	}

}
