package com.grekoff.lesson7.cart.services;

import com.grekoff.lesson7.api.ProductDto;

import com.grekoff.lesson7.cart.integrations.ProductsServiceIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.grekoff.lesson7.cart.utils.Cart;

import jakarta.annotation.PostConstruct;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductsServiceIntegration productsServiceIntegration;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
        cart.setItems(new ArrayList<>());
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addToCart(Long productId) {
        ProductDto p = productsServiceIntegration.findById(productId);
        cart.add(p);
    }

    public void deleteFromCart(Long productId) {
        if (cart.isPresentInCart(productId)) {
            cart.delete(productId);
        }
    }

    public void clearCart() {
        cart.clear();
    }
}
