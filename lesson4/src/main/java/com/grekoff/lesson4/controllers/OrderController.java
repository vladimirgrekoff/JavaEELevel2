package com.grekoff.lesson4.controllers;

import com.grekoff.lesson4.entities.User;
import com.grekoff.lesson4.services.OrdersService;
import com.grekoff.lesson4.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
