package com.example.aopassignment.controller;

import com.example.aopassignment.model.Category;
import com.example.aopassignment.model.Product;
import com.example.aopassignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id)
    {
        return productService.findById(id);
    }
    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }
    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product,@PathVariable int id){
        return productService.updateProduct(id,product);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
    }
    @GetMapping("/greater/{price}")
    public List<Product> getProductsGreaterThanPrice(@PathVariable double price){
        return productService.getProductsMoreThan(price);
    }
    @GetMapping( "catAndPriceLessThan/cat/")
    List<Product> findcatAndPriceLessThan(@RequestParam Category category, @RequestParam double maxPrice){
        return productService.findAllByCategoryAndPriceLessThan(category,maxPrice);
    }
    @GetMapping("/existingName/{keyword}")
    public List<Product> getExistingContainsName(@PathVariable String keyword){
        return productService.getExistingContainsName(keyword);
    }
}
