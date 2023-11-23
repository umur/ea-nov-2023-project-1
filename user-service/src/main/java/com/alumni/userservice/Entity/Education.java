package com.alumni.userservice.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String degree;
    private String university;
    private int graduationYear;
    @ManyToOne
    @JsonBackReference
    private Profile profile;

//    @JoinColumn(name = "education_id")
//    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//    private List<Course> courses;
}

