package com.grekoff.lesson6.core.properties;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
//import org.springframework.boot.context.properties.ConstructorBinding;

@Configuration
@ConfigurationProperties(prefix = "integrations.cart-service")
@Data
public class CartServiceIntegrationProperties {
    private String url;
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;
}
