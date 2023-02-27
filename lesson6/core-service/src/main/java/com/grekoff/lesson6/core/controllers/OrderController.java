package com.grekoff.lesson6.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.grekoff.lesson6.core.entities.User;
import com.grekoff.lesson6.core.services.OrdersService;
import com.grekoff.lesson6.core.services.UsersService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
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
