package com.example.aopassignment.repository;


import com.example.aopassignment.model.Category;
import com.example.aopassignment.model.Product;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends ListCrudRepository<Product,Integer> {
    Product findById(int id);

    Product updateById(int id,Product product);
    void deleteById(int id);
    List<Product> findAllByPriceGreaterThan(double Price);
    List<Product> findAllByCategoryAndPriceLessThan(Category category, double price);
    List<Product> findAllByNameContains(String keyword);
}