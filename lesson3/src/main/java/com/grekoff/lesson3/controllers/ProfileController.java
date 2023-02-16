package com.grekoff.lesson3.controllers;

import com.grekoff.lesson3.dtos.UserDto;
import com.grekoff.lesson3.entities.User;
import com.grekoff.lesson3.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final UsersService usersService;

    @GetMapping("/get")
    public UserDto getCurrentUserInfo(Principal principal) {
         User user = usersService.findByUsername(principal.getName()).get();
        return new UserDto(user.getId(), user.getUsername(), "******", user.getEmail(), user.getRoles());
    }

    @GetMapping("/roles")
    public UserDto getCurrentRoles(Principal principal) {
        User user = usersService.findByUsername(principal.getName()).get();
        return new UserDto(null, user.getUsername(), null, null, user.getRoles());
    }
}
