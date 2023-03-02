package com.grekoff.lesson7.auth.converters;


import com.grekoff.lesson7.auth.entities.User;
import com.grekoff.lesson7.api.UserDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserConverter {
    private final RoleConverter roleConverter;

    public UserConverter(RoleConverter roleConverter) {
        this.roleConverter = roleConverter;
    }

    public User dtoToEntity(UserDto userDto) {
        return new User(userDto.getId(), userDto.getUsername(), userDto.getPassword(), userDto.getEmail(), userDto.getRoles().stream().map(roleConverter::dtoToEntity).collect(Collectors.toList()));
    }

    public UserDto entityToDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getRoles().stream().map(roleConverter::entityToDto).collect(Collectors.toList()));
    }
}
