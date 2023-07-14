package com.projects.cv.smartdailydeals.controller;

import com.projects.cv.smartdailydeals.model.Category;
import com.projects.cv.smartdailydeals.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryResource {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable long id) {
        Optional<Category> categoryOptional = categoryService.getCategoryById(id);
        if(categoryOptional.isPresent()) {
            return categoryOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No category found with the provided 'id' value:"+id);
    }

    @PostMapping("/add")
    public void add(@RequestBody final Category category) {
        categoryService.addCategory(category);
    }

    @PutMapping("/update/{id}")
    public Category update(@PathVariable("id") final long id, @RequestBody Category category) {
        if (category.getId() != id)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provided update category's id mismatches provided id.");
        categoryService.updateCategory(id, category);
        return getCategory(id);
    }
}
