package com.grekoff.lesson5.cart.services;

import com.grekoff.lesson5.api.ProductDto;
import com.grekoff.lesson5.cart.integrations.ProductServiceIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.grekoff.lesson5.cart.utils.Cart;

import jakarta.annotation.PostConstruct;

import java.util.ArrayList;

//@Component
@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductServiceIntegration productServiceIntegration;
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
        ProductDto p = productServiceIntegration.findById(productId);
        cart.add(p);
    }

    public void deleteFromCart(Long productId) {
        if (cart.isPresentInCart(productId)) {
            cart.delete(productId);
        }
    }

//    public SelectedProduct addProductInCart(SelectedProduct selectedProduct) {
//        return cartRepository.addProduct(selectedProduct);
//    }

//    public SelectedProduct updateProductInCart(SelectedProduct selectedProduct) {
//        return cart.update(selectedProduct);
//    }

    public void clearCart() {
        cart.clear();
    }
}
