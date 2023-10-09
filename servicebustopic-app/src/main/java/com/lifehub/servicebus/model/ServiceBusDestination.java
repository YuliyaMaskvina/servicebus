package com.lifehub.servicebus.model;

import lombok.*;

import java.util.Objects;

@Data
@Builder(builderMethodName = "hiddenBuilder")
@AllArgsConstructor
public class ServiceBusDestination {
    private String destinationType;
    private String destinationName;
    private String namespaceName;
    private boolean enabled;

    public static ServiceBusDestinationBuilder builder(String destinationType, String destinationName, String namespace) {
        return new ServiceBusDestinationBuilder().destinationType(destinationType).destinationName(destinationName).namespaceName(namespace);
    }
}
