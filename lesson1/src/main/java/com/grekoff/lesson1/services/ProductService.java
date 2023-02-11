package com.grekoff.lesson1.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.grekoff.lesson1.dtos.ProductDto;
import com.grekoff.lesson1.entities.Product;
import com.grekoff.lesson1.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }
}
