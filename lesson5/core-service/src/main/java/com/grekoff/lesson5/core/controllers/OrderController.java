package com.grekoff.lesson5.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.grekoff.lesson5.core.entities.User;
import com.grekoff.lesson5.core.services.OrdersService;
import com.grekoff.lesson5.core.services.UsersService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final UsersService userService;
    private final OrdersService ordersService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Пользователь с таким именем не найден"));

        ordersService.createOrder(user);
    }
}
