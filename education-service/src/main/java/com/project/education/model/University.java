package com.project.education.model;

import com.project.education.model.base.BaseClass;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
public class University extends BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean  isDeleted;
    private String name;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<EduExperience> eduExperiences;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<Course> courses;
    // Constructors can be omitted because Lombok generates them
}
