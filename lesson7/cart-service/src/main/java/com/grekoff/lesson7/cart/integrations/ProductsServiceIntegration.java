package com.grekoff.lesson7.cart.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.grekoff.lesson7.api.ProductDto;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ProductsServiceIntegration {
    private final WebClient productsServiceWebClient;

    public ProductDto findById(Long id) {
        return productsServiceWebClient.get()
                .uri("/api/v1/products/" + id)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
    }
//
//    public void clear(String username) {
//        cartServiceWebClient.get()
//                .uri("/api/v1/cart/0/clear")
//                .header("username", username)
//                .retrieve()
//                .toBodilessEntity()
//                .block();
//    }
}
