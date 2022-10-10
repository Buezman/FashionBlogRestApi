package com.buezman.fashionblog.services.implementations;

import com.buezman.fashionblog.exception.ResourceNotFoundException;
import com.buezman.fashionblog.models.Category;
import com.buezman.fashionblog.repositories.CategoryRepository;
import com.buezman.fashionblog.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepo;

    public CategoryServiceImpl(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public ResponseEntity<Category> addCategory(Category category) {
        categoryRepo.save(category);
        return ResponseEntity.ok(category);
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("category not found"));
    }

    @Override
    public List<Category> viewAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public boolean categoryExists(String categoryName) {
        return categoryRepo.findCategoryByName(categoryName) != null;
    }

    @Override
    public ResponseEntity<Category> deleteCategory(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("category with id " + " not found"));
        categoryRepo.delete(category);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
