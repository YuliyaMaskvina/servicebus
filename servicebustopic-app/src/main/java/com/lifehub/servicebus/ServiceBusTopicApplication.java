package com.lifehub.servicebus;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.lifehub.servicebus.factory.ServiceBusClientFactory;
import com.lifehub.servicebus.model.ServiceBusDestinationType;
import com.lifehub.servicebus.model.ServiceBusDestination;
import com.lifehub.servicebus.producer.ServiceBusProducer;
import com.lifehub.servicebus.producer.impl.JsonServiceBusProducer;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class ServiceBusTopicApplication implements CommandLineRunner {
	private final ServiceBusClientFactory serviceBusClientFactory;

	public static void main(String[] args) {
		SpringApplication.run(ServiceBusTopicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		final ServiceBusDestination serviceBusDestination = ServiceBusDestination
				.builder(ServiceBusDestinationType.TOPIC.name(), "topic-temp", "swissjm.servicebus.windows.net")
				.enabled(Boolean.TRUE)
				.build();

		var serviceBusAsyncClient = serviceBusClientFactory.createAsyncClientSender(serviceBusDestination);

		var serviceBusProducer = new JsonServiceBusProducer(serviceBusAsyncClient);

		//serviceBusProducer.produceJsonMessageSync("{}");
		//var serviceBusProducer = new ServiceBusProducer(senderClient, serviceBusDestination);
		//serviceBusProducer.produceMessage("Test", "application/json");
	}
}
