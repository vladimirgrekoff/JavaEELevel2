package com.grekoff.lesson4.converters;


import com.grekoff.lesson4.dtos.UserDto;
import com.grekoff.lesson4.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User dtoToEntity(UserDto userDto) {
        return new User(userDto.getId(), userDto.getUsername(), userDto.getPassword(), userDto.getEmail(), userDto.getRoles());
    }

    public UserDto entityToDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getRoles());
    }
}
