package com.grekoff.lesson5.cart.converters;

import com.grekoff.lesson5.cart.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.grekoff.lesson5.api.CartDto;


import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {

    private final CartItemConverter cartItemConverter;

    public CartDto entityToDto(Cart c) {
        return new CartDto(c.getItems().stream().map(cartItemConverter::entityToDto).collect(Collectors.toList()), c.getTotalPrice());
    }

}
