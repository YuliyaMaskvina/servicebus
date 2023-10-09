package com.lifehub.servicebus.config.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "azure-service-bus-config")
public class ServiceBusClientConfigData {
    private boolean enabled;
    private String namespaceName;
    private RetryConfigData retryConfigData = new RetryConfigData();
}
