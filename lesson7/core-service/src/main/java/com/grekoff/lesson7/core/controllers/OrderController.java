package com.grekoff.lesson7.core.controllers;

import com.grekoff.lesson7.core.services.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
//@CrossOrigin("*")
public class OrderController {

//    private final UsersService userService;
    private final OrdersService ordersService;

    // http://localhost:8189/lesson7-core/api/v1/orders
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader("username") String username) {
//        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Пользователь с таким именем не найден"));

        ordersService.createOrder(username);
    }
}
