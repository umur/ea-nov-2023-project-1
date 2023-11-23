package com.project.education.model;

import com.project.education.model.base.TimeTrack;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
public class EduExperience extends TimeTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String degree;

    private Long alumniId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "EduExperience_Course",
            joinColumns = { @JoinColumn(name = "edu_experience_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private List<Course> courses;
    // Constructors can be omitted because Lombok generates them
}
