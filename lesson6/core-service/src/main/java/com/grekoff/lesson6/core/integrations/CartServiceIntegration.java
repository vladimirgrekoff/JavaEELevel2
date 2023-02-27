package com.grekoff.lesson6.core.integrations;

import com.grekoff.lesson6.api.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;

    public CartDto  getCurrentCart() {
        return restTemplate.getForObject("http://localhost:8190/lesson6-cart/api/v1/cart" , CartDto.class);
    }
    public void deleteAllProducts() {
        restTemplate.delete("http://localhost:8190/lesson6-cart/api/v1/cart/clear");
    }

}
