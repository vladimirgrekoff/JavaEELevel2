package com.grekoff.lesson2.converters;

import com.grekoff.lesson2.dtos.CartDto;
import com.grekoff.lesson2.dtos.CartItemDto;
import com.grekoff.lesson2.utils.Cart;
import com.grekoff.lesson2.utils.CartItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemConverter {

    public CartItem dtoToEntity(CartItemDto cartItemDto) {
        CartItem cartItem = new CartItem();
        cartItem.setProductId(cartItemDto.getProductId());
        cartItem.setProductTitle(cartItemDto.getProductTitle());
        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.setPricePerProduct(cartItemDto.getPricePerProduct());
        cartItem.setPrice(cartItemDto.getPrice());
        return cartItem;
    }

    public CartItemDto entityToDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setProductTitle(cartItem.getProductTitle());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());
        cartItemDto.setPrice(cartItem.getPrice());
        return cartItemDto;
    }

    public CartDto cartToDto(Cart cart) {
        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        List<CartItem> items = cart.getItems();
        BigDecimal totalPrice = cart.getTotalPrice();
        for (CartItem cartItem: items){
            CartItemDto cartItemDto = entityToDto(cartItem);
            cartItemDtoList.add(cartItemDto);
        }
        return new CartDto(cartItemDtoList, totalPrice);
    }

}
