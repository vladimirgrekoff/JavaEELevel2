package com.grekoff.lesson1.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDto {
    private String title;
    private BigDecimal price;
}
