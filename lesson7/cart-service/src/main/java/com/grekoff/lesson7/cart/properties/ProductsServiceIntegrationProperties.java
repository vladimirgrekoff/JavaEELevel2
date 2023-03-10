package com.grekoff.lesson7.cart.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
//import org.springframework.boot.context.properties.ConstructorBinding;

@Configuration
@ConfigurationProperties(prefix = "integrations.product-service")
@Data
public class ProductsServiceIntegrationProperties {
    private String url;
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;
}
