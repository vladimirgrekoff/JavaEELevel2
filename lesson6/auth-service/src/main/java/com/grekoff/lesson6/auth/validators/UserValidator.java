package com.grekoff.lesson6.auth.validators;

import com.grekoff.lesson6.api.UserDto;
import com.grekoff.lesson6.auth.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {
    public void validate(UserDto userDto) {
        List<String> errors = new ArrayList<>();

        if (userDto.getUsername().isBlank()) {
            errors.add("Пользователь не может быть без имени");
        }

        if (userDto.getPassword().isBlank()) {
            errors.add("Пароль не может иметь пустое название");
        }

        if (userDto.getEmail().isBlank()) {
            errors.add("Злектронная почта не может иметь пустое название");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}

