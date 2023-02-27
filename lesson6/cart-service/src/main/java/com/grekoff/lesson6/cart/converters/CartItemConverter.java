package com.grekoff.lesson6.cart.converters;

import com.grekoff.lesson6.cart.utils.CartItem;
import org.springframework.stereotype.Component;
import com.grekoff.lesson6.api.CartItemDto;

@Component
public class CartItemConverter {

    public CartItemDto entityToDto(CartItem c) {
        return new CartItemDto(c.getProductId(), c.getProductTitle(), c.getQuantity(), c.getPricePerProduct(), c.getPrice());
    }

}
