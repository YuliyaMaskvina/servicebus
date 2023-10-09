package com.lifehub.servicebus.config.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "azure-service-bus-config.credential")
public class AzureCredentialConfigData {
    private String clientId;
    private String tenantId;
    private String clientSecret;
    private String certificatePath;
    private String certificatePassword;
    private boolean managedIdentityEnabled;
}
