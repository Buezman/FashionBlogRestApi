package com.buezman.fashionblog.services;

import com.buezman.fashionblog.models.Category;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface CategoryService {

    ResponseEntity<Category> addCategory(Category category);

    Category findCategoryById(Long id);

    List<Category> viewAllCategories();

    boolean categoryExists(String categoryName);

    ResponseEntity<Category> deleteCategory(Long id);
}
