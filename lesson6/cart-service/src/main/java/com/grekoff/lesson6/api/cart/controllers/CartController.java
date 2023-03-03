package com.grekoff.lesson6.api.cart.controllers;


import com.grekoff.lesson6.api.JwtRequest;
import com.grekoff.lesson6.api.JwtResponse;
import com.grekoff.lesson6.api.cart.converters.CartConverter;
import com.grekoff.lesson6.api.cart.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grekoff.lesson6.api.CartDto;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
//@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    // http://localhost:8190/lesson6-cart/api/v1/cart
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

