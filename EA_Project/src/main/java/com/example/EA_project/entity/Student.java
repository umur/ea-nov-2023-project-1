package com.example.EA_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Entity
@Getter
@Setter
public class Student  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String graduationYear;
    private String description;
    private String category;
    private String industry;

    @Embedded
    private Address address;

    @ElementCollection
    private   List<String> courses;
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Job> jobs;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}

