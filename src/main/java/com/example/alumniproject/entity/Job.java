package com.example.alumniproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Job extends BaseEntity {

    @OneToOne
    @JsonBackReference
    private Profile poster;

    private String title;

    private String description;

    private String organization;

    @OneToOne
    @JsonBackReference
    private Profile assigner;

    @OneToOne
    private Location location;
}
