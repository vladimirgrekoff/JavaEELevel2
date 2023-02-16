package com.grekoff.lesson3.services;


import com.grekoff.lesson3.aop.TimerExecution;
import com.grekoff.lesson3.converters.CartItemConverter;
import com.grekoff.lesson3.dtos.CartDto;
import com.grekoff.lesson3.entities.Order;
import com.grekoff.lesson3.entities.OrderItem;
import com.grekoff.lesson3.entities.User;
import com.grekoff.lesson3.repositories.OrdersRepository;
import com.grekoff.lesson3.utils.Cart;
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
    public void createOrder(User user) {

        Cart currentCart = cartService.getCurrentCart();
        CartDto cartDto = cartItemConverter.cartToDto(currentCart);
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setEmail(user.getEmail());
        order.setOrderItem(cartDto.getItems().stream().map(cartItem -> new OrderItem(productsService.findById(cartItem.getProductId()).get(),
                        order, cartItem.getQuantity(), cartItem.getPricePerProduct(), cartItem.getPrice())).collect(Collectors.toList()));
        ordersRepository.save(order);
        cartService.clearCart();
    }
}