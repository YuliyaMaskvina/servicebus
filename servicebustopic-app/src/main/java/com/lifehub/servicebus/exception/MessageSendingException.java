package com.lifehub.servicebus.exception;

public class MessageSendingException extends Exception {
    public MessageSendingException(String message) {
        super(message);
    }

    public MessageSendingException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
