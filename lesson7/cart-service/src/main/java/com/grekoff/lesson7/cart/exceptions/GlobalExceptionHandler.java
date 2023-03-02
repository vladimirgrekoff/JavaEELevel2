package com.grekoff.lesson7.cart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<com.grekoff.lesson7.cart.exceptions.AppError> catchResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new com.grekoff.lesson7.cart.exceptions.AppError("RESOURCE_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
