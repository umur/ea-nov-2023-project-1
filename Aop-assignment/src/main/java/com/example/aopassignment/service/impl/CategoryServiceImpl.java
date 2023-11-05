package com.example.aopassignment.service.impl;

import com.example.aopassignment.model.Category;
import com.example.aopassignment.repository.CategoryRepo;
import com.example.aopassignment.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    @Override
    public Category findById(int id) {
        return categoryRepo.findById(id);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category updateCategory(int id, Category category) {
        return categoryRepo.updateById(id,category);
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepo.deleteById(id);
    }
}
