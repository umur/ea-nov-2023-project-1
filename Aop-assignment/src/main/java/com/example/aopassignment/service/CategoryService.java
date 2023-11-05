package com.example.aopassignment.service;

import com.example.aopassignment.model.Category;

public interface CategoryService {
    Category findById(int id);
    Category saveCategory(Category category);
    Category updateCategory(int id,Category category);
    void deleteCategory(int id);
}
