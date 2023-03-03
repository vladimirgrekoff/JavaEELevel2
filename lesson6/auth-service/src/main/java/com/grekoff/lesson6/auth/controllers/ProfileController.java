package com.grekoff.lesson6.auth.controllers;

import com.grekoff.lesson6.api.JwtResponse;
import com.grekoff.lesson6.api.UserDto;
import com.grekoff.lesson6.auth.converters.RoleConverter;
import com.grekoff.lesson6.auth.entities.User;
import com.grekoff.lesson6.auth.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
//@CrossOrigin("*")
public class ProfileController {
    private final UsersService usersService;
    private final RoleConverter roleConverter;

    // http://localhost:8187/lesson6-auth/api/v1/profile
    @GetMapping("/get")
    public UserDto getCurrentUserInfo(@RequestHeader String username) {
         User user = usersService.findByUsername(username).get();
        return new UserDto(user.getId(), user.getUsername(), "******", user.getEmail(), user.getRoles().stream().map(roleConverter::entityToDto).collect(Collectors.toList()));
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getCurrentRoles(@RequestHeader String username) {
        User user = usersService.findByUsername(username).get();
        return ResponseEntity.ok(new UserDto(null, user.getUsername(), null, null, user.getRoles().stream().map(roleConverter::entityToDto).collect(Collectors.toList())));
    }
}
