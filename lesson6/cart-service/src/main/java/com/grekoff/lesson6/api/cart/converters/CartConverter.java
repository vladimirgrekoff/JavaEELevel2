package com.grekoff.lesson6.api.cart.converters;

import com.grekoff.lesson6.api.cart.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.grekoff.lesson6.api.CartDto;


import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {

    private final CartItemConverter cartItemConverter;

    public CartDto entityToDto(Cart c) {
        return new CartDto(c.getItems().stream().map(cartItemConverter::entityToDto).collect(Collectors.toList()), c.getTotalPrice());
    }

}
