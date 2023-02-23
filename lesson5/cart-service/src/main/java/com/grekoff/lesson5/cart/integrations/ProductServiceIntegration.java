package com.grekoff.lesson5.cart.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.grekoff.lesson5.api.ProductDto;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    public ProductDto findById(Long id) {
        return restTemplate.getForObject("http://localhost:8189/lesson5-core/api/v1/products/" + id, ProductDto.class);
    }
}
