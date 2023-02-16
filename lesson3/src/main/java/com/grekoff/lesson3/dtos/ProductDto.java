package com.grekoff.lesson3.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private String categoryTitle;
}
