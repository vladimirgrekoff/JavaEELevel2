package com.grekoff.lesson5.cart.converters;

import org.springframework.stereotype.Component;
import com.grekoff.lesson5.api.CartItemDto;
import com.grekoff.lesson5.cart.utils.CartItem;

@Component
public class CartItemConverter {

    public CartItemDto entityToDto(CartItem c) {
        return new CartItemDto(c.getProductId(), c.getProductTitle(), c.getQuantity(), c.getPricePerProduct(), c.getPrice());
    }

}
