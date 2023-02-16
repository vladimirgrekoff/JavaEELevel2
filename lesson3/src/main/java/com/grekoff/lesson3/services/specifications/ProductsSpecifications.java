package com.grekoff.lesson3.services.specifications;

import com.grekoff.lesson3.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductsSpecifications {
    public static Specification<Product> priceGreaterOrEqualsThan(Integer price){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> priceLessOrEqualsThan(Integer price){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> titleLike(String partTitle){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", partTitle));
    }

}
