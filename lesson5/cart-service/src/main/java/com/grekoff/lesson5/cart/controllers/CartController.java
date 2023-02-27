package com.grekoff.lesson5.cart.controllers;


import com.grekoff.lesson5.cart.converters.CartConverter;
import com.grekoff.lesson5.cart.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.grekoff.lesson5.api.CartDto;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    // GET http://localhost:8189/lesson5-cart/api/v1/cart
    @GetMapping
    public CartDto getCurrentCart() {
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
    public void deleteAllProducts(){
        cartService.clearCart();
    }

}

