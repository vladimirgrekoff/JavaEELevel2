package com.grekoff.lesson4.repositories;

import com.grekoff.lesson4.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c where c.title = ?1")
    Optional<Category> findByTitle(String title);
}