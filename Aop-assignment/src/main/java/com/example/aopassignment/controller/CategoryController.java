package com.example.aopassignment.controller;

import com.example.aopassignment.model.Category;
import com.example.aopassignment.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable int id)
    {
        return categoryService.findById(id);
    }
    @PostMapping
    public Category saveCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }
    @PutMapping("/{id}")
    public Category updateCategory(@RequestBody Category category,@PathVariable int id){
        return categoryService.updateCategory(id,category);
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
    }

}
