package com.grekoff.lesson2.controllers;


import com.grekoff.lesson2.dtos.CartDto;
import com.grekoff.lesson2.dtos.CartItemDto;
import com.grekoff.lesson2.converters.CartItemConverter;
import com.grekoff.lesson2.services.CartService;
import com.grekoff.lesson2.utils.Cart;
import com.grekoff.lesson2.utils.CartItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final CartService cartService;
    private final CartItemConverter cartItemConverter;

    // GET http://localhost:8189/lesson2/cart

    @GetMapping
    public CartDto getCurrentCart() {
        Cart currentCart = cartService.getCurrentCart();
        return cartItemConverter.cartToDto(currentCart);
    }

    @GetMapping("/add/{productId}")
    public void addProductToCart(@PathVariable Long productId) {
        cartService.addToCart(productId);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProductById(@PathVariable(name = "productId") Long productId){
        cartService.deleteFromCart(productId);
    }

    @GetMapping("/clear")
    public void deleteAll(){
        cartService.clearCart();
    }

}

