package com.miu.alumnimanagementportal.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class EducationDetail extends BaseEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private String institutionName;
    private String degree;
    private Date passingYear;
    private String cgpa;
    private String course;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

}
