package com.grekoff.lesson5.core.integrations;

import com.grekoff.lesson5.api.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;

    public CartDto  getCurrentCart() {
        return restTemplate.getForObject("http://localhost:8190/lesson5-cart/api/v1/cart" , CartDto.class);
    }
    public void deleteAll() {
        restTemplate.getForObject("http://localhost:8190/lesson5-cart/api/v1/cart/clear" , CartDto.class);
    }

}
