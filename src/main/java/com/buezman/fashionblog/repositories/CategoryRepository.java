package com.buezman.fashionblog.repositories;

import com.buezman.fashionblog.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotBlank;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(@NotBlank String name);
}
