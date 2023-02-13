package com.grekoff.lesson2.services;

import com.grekoff.lesson2.utils.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.grekoff.lesson2.exceptions.ResourceNotFoundException;
import com.grekoff.lesson2.entities.Product;
import com.grekoff.lesson2.utils.Cart;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

//@Component
@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductsService productsService;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }



    public void addToCart(Long productId) {
        Product p = productsService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + productId + " не найден"));
        tempCart.add(p);
    }

    public void deleteFromCart(Long productId) {
        if (tempCart.isPresentInCart(productId)) {
            tempCart.delete(productId);
        }
    }

//    public SelectedProduct addProductInCart(SelectedProduct selectedProduct) {
//        return cartRepository.addProduct(selectedProduct);
//    }

//    public SelectedProduct updateProductInCart(SelectedProduct selectedProduct) {
//        return cart.update(selectedProduct);
//    }

    public void clearCart() {
        tempCart.clear();
    }
}
