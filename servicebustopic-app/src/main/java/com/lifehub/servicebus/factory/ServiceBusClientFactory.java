package com.lifehub.servicebus.factory;
import com.azure.core.credential.TokenCredential;
import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusSenderAsyncClient;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.lifehub.servicebus.config.data.ServiceBusClientConfigData;
import com.lifehub.servicebus.model.ServiceBusDestinationType;
import com.lifehub.servicebus.model.ServiceBusDestination;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ServiceBusClientFactory {
    private final TokenCredential tokenCredential;
    private final ServiceBusClientConfigData serviceBusClientConfigData;

    public ServiceBusSenderAsyncClient createAsyncClientSender(final ServiceBusDestination destination) {
        validateServiceBusDestination(destination);

        var clientBuilder = new ServiceBusClientBuilder()
                .fullyQualifiedNamespace(destination.getNamespaceName())
                .credential(tokenCredential);
        return ServiceBusDestinationType.TOPIC.name().equals(destination.getDestinationType())
                ? createTopicSender(clientBuilder, destination.getDestinationName()) : createQueueSender(clientBuilder, destination.getDestinationName());
    }

    private ServiceBusSenderAsyncClient createTopicSender(ServiceBusClientBuilder busClientBuilder, String topic) {
        return busClientBuilder.sender().topicName(topic).buildAsyncClient();
    }

    private ServiceBusSenderAsyncClient createQueueSender(ServiceBusClientBuilder busClientBuilder, String queue) {
        return busClientBuilder.sender().topicName(queue).buildAsyncClient();
    }

    private void validateServiceBusDestination(final ServiceBusDestination destination) {
        var destinationType = ServiceBusDestinationType.getByName(destination.getDestinationName());

        if(!destinationType.isPresent()) {
            throw new IllegalArgumentException("Cannot recognise the destination provided. Possible values are: " + ServiceBusDestinationType.values());
        }
    }

}
