package com.example.aopassignment.model;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@Setter
@Getter
public class Review {
    @Id
    private int id;
    private String comment;
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
}