package com.grekoff.lesson7.auth.controllers;

import com.grekoff.lesson7.auth.converters.RoleConverter;
import com.grekoff.lesson7.auth.entities.User;
import com.grekoff.lesson7.auth.services.UsersService;
import com.grekoff.lesson7.api.UserDto;
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

    @GetMapping("/get")
    public UserDto getCurrentUserInfo(Principal principal) {
         User user = usersService.findByUsername(principal.getName()).get();
        return new UserDto(user.getId(), user.getUsername(), "******", user.getEmail(), user.getRoles().stream().map(roleConverter::entityToDto).collect(Collectors.toList()));
    }

    @GetMapping("/roles")
//    public UserDto getCurrentRoles(Principal principal) {
    public ResponseEntity<?> getCurrentRoles(@RequestHeader String username) {
        User user = usersService.findByUsername(username).get();
//        User user = usersService.findByUsername(principal.getName()).get();
//        return new UserDto(null, user.getUsername(), null, null, user.getRoles().stream().map(roleConverter::entityToDto).collect(Collectors.toList()));
        return ResponseEntity.ok(new UserDto(null, user.getUsername(), null, null, user.getRoles().stream().map(roleConverter::entityToDto).collect(Collectors.toList())));
    }
}
