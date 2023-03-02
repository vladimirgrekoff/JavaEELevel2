package com.grekoff.lesson7.cart.converters;

import com.grekoff.lesson7.cart.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.grekoff.lesson7.api.CartDto;


import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {

    private final CartItemConverter cartItemConverter;

    public CartDto entityToDto(Cart c) {
        return new CartDto(c.getItems().stream().map(cartItemConverter::entityToDto).collect(Collectors.toList()), c.getTotalPrice());
    }

}
