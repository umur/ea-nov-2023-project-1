package com.example.aopassignment.service;

import com.example.aopassignment.model.Category;
import com.example.aopassignment.model.Product;


import java.util.List;

public interface ProductService {
    Product findById(int id);
    Product saveProduct(Product product);
    Product updateProduct(int id,Product product);
    void deleteProduct(int id);
    List<Product> getProductsMoreThan(double minPrice);
    List<Product> findAllByCategoryAndPriceLessThan(Category category, double price);
    List<Product> getExistingContainsName(String keyword);

}