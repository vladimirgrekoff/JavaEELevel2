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
        return cartServiceWebClient.get()
                .uri("/api/v1/cart")
                .header("username", username)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clear(String username) {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/clear")
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

}
