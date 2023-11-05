package com.example.aopassignment.service.impl;

import com.example.aopassignment.model.Category;
import com.example.aopassignment.model.Product;
import com.example.aopassignment.repository.ProductRepo;
import com.example.aopassignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    @Override
    public Product findById(int id) {
        return productRepo.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(int id, Product product) {
        return productRepo.updateById(id,product);
    }

    @Override
    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }

    @Override
    public List<Product> getProductsMoreThan(double minPrice) {
        return productRepo.findAllByPriceGreaterThan(minPrice);
    }

    @Override
    public List<Product> findAllByCategoryAndPriceLessThan(Category category, double price) {
        return productRepo.findAllByCategoryAndPriceLessThan(category,price);
    }

    @Override
    public List<Product> getExistingContainsName(String keyword) {
        return productRepo.findAllByNameContains(keyword);
    }
}