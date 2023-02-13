package com.grekoff.lesson2.converters;

import com.grekoff.lesson2.dtos.ProductDto;
import com.grekoff.lesson2.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product dtoToEntity(ProductDto pDto) {
        Product product = new Product();
        product.setId(pDto.getId());
        product.setTitle(pDto.getTitle());
        product.setPrice(pDto.getPrice());
        return product;
    }

    public ProductDto entityToDto(Product p) {
        ProductDto productDto = new ProductDto();
        productDto.setId(p.getId());
        productDto.setTitle(p.getTitle());
        productDto.setPrice(p.getPrice());
        productDto.setCategoryTitle(p.getCategory().getTitle());
        return productDto;
    }

}
