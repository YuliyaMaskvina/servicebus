package com.lifehub.servicebus.config.data;

import com.azure.core.amqp.AmqpRetryMode;
import lombok.Data;
import java.time.Duration;

@Data
public class RetryConfigData {
    private int maxAttempts = 3;
    private Duration delay = Duration.ofMillis(800L);
    private Duration maxDelay = Duration.ofMinutes(1L);
    private Duration tryTimeout = Duration.ofMinutes(1L);
    private AmqpRetryMode retryMode = AmqpRetryMode.EXPONENTIAL;
}
