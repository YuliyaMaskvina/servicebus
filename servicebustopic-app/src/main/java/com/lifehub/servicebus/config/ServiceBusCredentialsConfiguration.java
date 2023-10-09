package com.lifehub.servicebus.config;
import com.azure.core.credential.TokenCredential;
import com.azure.identity.ClientCertificateCredentialBuilder;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.lifehub.servicebus.config.data.AzureCredentialConfigData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix="azure-service-bus-config", name = "enabled", havingValue = "true")
@AllArgsConstructor
public class ServiceBusCredentialsConfiguration {

    private final AzureCredentialConfigData azureAuthenticationProperties;

    @Bean
    @ConditionalOnProperty(prefix="azure-service-bus-config.credential", name = "client-secret")
    TokenCredential clientSecretCredential() {
        return new ClientSecretCredentialBuilder()
                .clientId(azureAuthenticationProperties.getClientId())
                .tenantId(azureAuthenticationProperties.getTenantId())
                .clientSecret(azureAuthenticationProperties.getClientSecret())
                .build();
    }

    @Bean
    @ConditionalOnProperty(prefix="azure-service-bus-config.credential", name = "client-certificate-path")
    TokenCredential clientCertificateCredential() {
        return new ClientCertificateCredentialBuilder()
                .clientId(azureAuthenticationProperties.getClientId())
                .tenantId(azureAuthenticationProperties.getTenantId())
                .pfxCertificate("", "")
                .build();
    }

    @Bean
    @ConditionalOnProperty(prefix="azure-service-bus-config.credential", name = "managed-identity-enabled")
    TokenCredential managedEntityCredential() {
        return new DefaultAzureCredentialBuilder().build();
    }

}
