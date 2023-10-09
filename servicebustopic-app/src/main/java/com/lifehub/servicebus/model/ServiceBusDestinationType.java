package com.lifehub.servicebus.model;

import java.util.Arrays;
import java.util.Optional;

public enum ServiceBusDestinationType {
    TOPIC, QUEUE;

    public static Optional<ServiceBusDestinationType> getByName(String name) {
        return Arrays.stream(ServiceBusDestinationType.values()).filter(destinationType -> destinationType.name().equalsIgnoreCase(name)).findFirst();
    }
}
