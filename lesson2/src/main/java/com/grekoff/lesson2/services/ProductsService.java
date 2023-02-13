package com.grekoff.lesson2.services;

import com.grekoff.lesson2.exceptions.ResourceNotFoundException;
import com.grekoff.lesson2.services.specifications.ProductsSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.grekoff.lesson2.dtos.ProductDto;
import com.grekoff.lesson2.entities.Product;
import com.grekoff.lesson2.repositories.ProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final CategoryService categoryService;
    public int page = 0;

    public Page<Product> findAll(Integer minPrice, Integer maxPrice, String partTitle, Integer offset, Integer size) {

        checkFirstNumberPage(offset);

        Specification<Product> spec = Specification.where(null);

        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecifications.titleLike(partTitle));
        }
//        return productsRepository.findAll(spec, PageRequest.of(page,size));

        Page<Product> pageRepository = productsRepository.findAll(spec, PageRequest.of(page, size));

        if (pageRepository.stream().count() > 0) {
            return pageRepository;
        } else {
            page = page - 1;
            return productsRepository.findAll(spec, PageRequest.of(page, size));
        }
    }

        private void checkFirstNumberPage(Integer offset) {
            if (offset != 0) {
                page = page + offset;
                if (page < 0) {
                    page = 0;
                }
            } else {
                page = 0;
            }
        }

    public Optional<Product> findById(Long id) {
        return productsRepository.findById(id);
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public void createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Категория с названием: " + productDto.getCategoryTitle() + " не найдена")));
        productsRepository.save(product);
    }
}
