package com.grekoff.lesson5.core.services;



import com.grekoff.lesson5.api.CartDto;
import com.grekoff.lesson5.core.entities.Order;
import com.grekoff.lesson5.core.entities.OrderItem;
import com.grekoff.lesson5.core.entities.User;
import com.grekoff.lesson5.core.entities.Product;
import com.grekoff.lesson5.core.converters.ProductConverter;
import com.grekoff.lesson5.core.integrations.CartServiceIntegration;
import com.grekoff.lesson5.core.repositories.OrdersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final ProductsService productsService;
    private final OrdersRepository ordersRepository;
    private final CartServiceIntegration cartServiceIntegration;


    @Transactional
    public void createOrder(User user) {
        CartDto cartDto = cartServiceIntegration.getCurrentCart();

        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setEmail(user.getEmail());
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
//        cartServiceIntegration.deleteAll();
    }
}