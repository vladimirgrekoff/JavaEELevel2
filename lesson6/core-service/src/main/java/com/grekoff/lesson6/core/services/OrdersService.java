package com.grekoff.lesson6.core.services;

import com.grekoff.lesson6.api.CartDto;
import com.grekoff.lesson6.api.UserDto;
import com.grekoff.lesson6.core.entities.Order;
import com.grekoff.lesson6.core.entities.OrderItem;
import com.grekoff.lesson6.core.integrations.CartServiceIntegration;
import com.grekoff.lesson6.core.integrations.AuthServiceIntegration;
import com.grekoff.lesson6.core.repositories.OrdersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final ProductsService productsService;
    private final OrdersRepository ordersRepository;
    private final CartServiceIntegration cartServiceIntegration;
    private final AuthServiceIntegration authServiceIntegration;


    @Transactional
    public void createOrder(String username) {
        UserDto userDto = authServiceIntegration.getCurrentUserInfo(username);
        System.out.println("пользователь " + userDto);/////////////////////////////////////
        CartDto cartDto = cartServiceIntegration.getCurrentCart(username);
        System.out.println("корзина " + cartDto);/////////////////////////////////////
        Order order = new Order();
        order.setUser_id(userDto.getId());
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setEmail(userDto.getEmail());
        order.setOrderItem(cartDto.getItems().stream().map(
                cartItem -> new OrderItem(
                        productsService.findById(cartItem.getProductId()).get(),
                        order,
                        cartItem.getQuantity(),
                        cartItem.getPricePerProduct(),
                        cartItem.getPrice()
                )
        ).collect(Collectors.toList()));
        ordersRepository.save(order);
        cartServiceIntegration.clear(username);
    }
}