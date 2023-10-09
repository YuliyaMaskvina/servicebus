package com.lifehub.servicebus.producer.impl;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderAsyncClient;
import com.lifehub.servicebus.exception.MessageSendingException;
import com.lifehub.servicebus.producer.ServiceBusProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Send messages to the ServiceBus
 */
@Slf4j
@AllArgsConstructor
public class JsonServiceBusProducer implements ServiceBusProducer<String> {
    private final ServiceBusSenderAsyncClient serviceBusSenderClient;
    private static final String CONTENT_TYPE = "application/json";

    public void produceMessageSync(final String message) throws MessageSendingException {
        final var serviceBusMessage = new ServiceBusMessage(message);
        serviceBusMessage.setContentType(CONTENT_TYPE);
        try {
            serviceBusSenderClient.sendMessage(serviceBusMessage).block();
        } catch (Exception ex) {
            log.error("Exception occurred while sending the message {}", serviceBusSenderClient.getEntityPath());
            log.error(" by cause {} , {}", ex.getMessage(), ex.getStackTrace());
            //save to the database or create an event for that
            throw new MessageSendingException("Exception occurred while sending the message {}", ex);
        }

    }
}
