package com.grekoff.lesson7.core.tests;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.grekoff.lesson7.api.ProductDto;
import com.grekoff.lesson7.core.entities.Category;
import com.grekoff.lesson7.core.repositories.ProductsRepository;
import com.grekoff.lesson7.core.services.CategoryService;
import com.grekoff.lesson7.core.services.ProductsService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

@SpringBootTest(classes = ProductsService.class)
public class ProductServiceTests {
    @Autowired
    private ProductsService productsService;

    @MockBean
    private ProductsRepository productsRepository;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void createNewProductTest() {
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Продукты");
        category.setProducts(Collections.emptyList());
        Mockito.doReturn(Optional.of(category))
                .when(categoryService)
                .findByTitle("Продукты");

        ProductDto productDto = new ProductDto(null, "Апельсины", BigDecimal.valueOf(100.0), "Продукты");
        productsService.createNewProduct(productDto);

        Mockito.verify(productsRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }
}
