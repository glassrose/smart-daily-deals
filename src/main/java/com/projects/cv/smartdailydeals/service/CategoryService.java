package com.projects.cv.smartdailydeals.service;

import com.projects.cv.smartdailydeals.model.Category;
import com.projects.cv.smartdailydeals.model.Item;

import java.util.Optional;

public interface CategoryService {
    void addCategory(Category category);
    Optional<Category> updateCategory(long id, Category update);
    Optional<Category> getCategoryById(Long id);
}
