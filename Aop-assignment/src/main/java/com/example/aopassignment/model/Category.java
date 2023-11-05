package com.example.aopassignment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Category {
    @Id
    private int id;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}