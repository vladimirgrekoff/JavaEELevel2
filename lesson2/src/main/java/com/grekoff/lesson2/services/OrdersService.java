package com.grekoff.lesson2.services;

import com.grekoff.lesson2.converters.CartItemConverter;
import com.grekoff.lesson2.dtos.CartDto;
import com.grekoff.lesson2.entities.Order;
import com.grekoff.lesson2.entities.OrderItem;
import com.grekoff.lesson2.entities.User;
import com.grekoff.lesson2.repositories.OrdersRepository;
import com.grekoff.lesson2.utils.Cart;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final ProductsService productsService;
    private final OrdersRepository ordersRepository;
    private final CartService cartService;
    private final CartItemConverter cartItemConverter;

    @Transactional
    public void createOrder( ) {

        Cart currentCart = cartService.getCurrentCart();
        CartDto cartDto = cartItemConverter.cartToDto(currentCart);
        Order order = new Order();
//        order.setUser(user);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setOrderItem(cartDto.getItems().stream().map(cartItem -> new OrderItem(productsService.findById(cartItem.getProductId()).get(),
                        order, cartItem.getQuantity(), cartItem.getPricePerProduct(), cartItem.getPrice())).collect(Collectors.toList()));
        ordersRepository.save(order);
        cartService.clearCart();
    }
}