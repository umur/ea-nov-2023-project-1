package edu.ea.jobservice.model;

import com.example.EA_project.entity.Job;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String graduationYear;
    private String description;
    private String category;
    private String industry;


}

