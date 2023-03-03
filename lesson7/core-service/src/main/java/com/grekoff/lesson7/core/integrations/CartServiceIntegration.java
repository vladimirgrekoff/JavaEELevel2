package com.grekoff.lesson7.core.integrations;

import com.grekoff.lesson7.api.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public CartDto getCurrentCart(String username) {
//                .uri("http://localhost:8190/lesson7-cart/api/v1/cart")
        return cartServiceWebClient.get()
                .uri("/api/v1/cart")
                .header("username", username)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clear(String username) {
        cartServiceWebClient.delete()
                .uri("/api/v1/cart/clear")
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

}
