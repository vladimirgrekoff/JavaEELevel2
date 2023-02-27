package com.grekoff.lesson6.core.validators;

import com.grekoff.lesson6.api.ProductDto;
import com.grekoff.lesson6.core.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();



        if (productDto.getTitle().isBlank()) {
            errors.add("Продукт не может иметь пустое название");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}

