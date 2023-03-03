package com.grekoff.lesson7.cart.controllers;

import com.grekoff.lesson7.cart.converters.CartConverter;
import com.grekoff.lesson7.cart.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.grekoff.lesson7.api.CartDto;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
//@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    // http://localhost:8190/lesson7-cart/api/v1/cart
    @GetMapping

    public CartDto getCurrentCart(@RequestHeader("username") String username) {
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }

    @GetMapping("/add/{productId}")
    public void addProductToCart(@PathVariable Long productId) {
        cartService.addToCart(productId);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProductById(@PathVariable(name = "productId") Long productId){
        cartService.deleteFromCart(productId);
    }

    @DeleteMapping("/clear")
    public void deleteAllProducts(@RequestHeader("username") String username){
        cartService.clearCart();
    }

}

