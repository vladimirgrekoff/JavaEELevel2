package com.grekoff.lesson5.core.services.specifications;

import com.grekoff.lesson5.core.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class UsersSpecifications {

    public static Specification<User> idGreaterOrEqualsThan(Long id){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("id"), id);
    }

    public static Specification<User> idLessOrEqualsThan(Long id){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("id"), id);
    }

    public static Specification<User> usernameLike(String partUsername){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("username"), String.format("%%%s%%", partUsername));
    }

}
