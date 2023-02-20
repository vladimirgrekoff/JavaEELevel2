package com.grekoff.lesson4.services;

import com.grekoff.lesson4.entities.Category;
import com.grekoff.lesson4.repositories.CategoryRepository;
import com.grekoff.lesson4.soap.categories.CategorySoap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }


    //////////////////// SOAP ///////////////////////////////////////
    public static final Function<Category, CategorySoap> functionEntityToSoap = category -> {
        CategorySoap categorySoap = new CategorySoap();
        categorySoap.setTitle(category.getTitle());
        category.getProducts().stream().map(ProductsService.functionEntityToSoap).forEach(productSoap -> categorySoap.getProducts().add(productSoap));
        return categorySoap;
    };

    public CategorySoap getByTitle(String title) {
        return categoryRepository.findByTitle(title).map(functionEntityToSoap).get();
    }
}
