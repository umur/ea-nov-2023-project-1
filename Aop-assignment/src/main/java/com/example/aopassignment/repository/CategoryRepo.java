package com.example.aopassignment.repository;

import com.example.aopassignment.model.Category;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends ListCrudRepository<Category,Integer> {
    Category findById(int id);

    Category updateById(int id,Category category);
    void deleteById(int id);
}