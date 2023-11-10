package com.example.EA_project.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appId;
    private boolean isDeleted;
    @ManyToOne
    private User student;

}