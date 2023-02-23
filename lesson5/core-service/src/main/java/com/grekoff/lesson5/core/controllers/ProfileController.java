package com.grekoff.lesson5.core.controllers;

import com.grekoff.lesson5.api.UserDto;
import com.grekoff.lesson5.core.converters.RoleConverter;
import com.grekoff.lesson5.core.entities.User;
import com.grekoff.lesson5.core.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final UsersService usersService;
    private final RoleConverter roleConverter;

    @GetMapping("/get")
    public UserDto getCurrentUserInfo(Principal principal) {
         User user = usersService.findByUsername(principal.getName()).get();
        return new UserDto(user.getId(), user.getUsername(), "******", user.getEmail(), user.getRoles().stream().map(roleConverter::entityToDto).collect(Collectors.toList()));
    }

    @GetMapping("/roles")
    public UserDto getCurrentRoles(Principal principal) {
        User user = usersService.findByUsername(principal.getName()).get();
        return new UserDto(null, user.getUsername(), null, null, user.getRoles().stream().map(roleConverter::entityToDto).collect(Collectors.toList()));
    }
}
