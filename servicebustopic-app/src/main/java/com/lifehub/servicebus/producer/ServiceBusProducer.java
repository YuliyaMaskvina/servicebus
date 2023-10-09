package com.lifehub.servicebus.producer;

import com.lifehub.servicebus.exception.MessageSendingException;

public interface ServiceBusProducer<T> {
    void produceMessageSync(T message) throws MessageSendingException;
}
