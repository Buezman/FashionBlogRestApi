package com.buezman.fashionblog.controllers;

import com.buezman.fashionblog.models.Category;
import com.buezman.fashionblog.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("add")
    public ResponseEntity<Category> addNewCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @GetMapping
    public List<Category> viewAllCategories() {
        return categoryService.viewAllCategories();
    }


}
