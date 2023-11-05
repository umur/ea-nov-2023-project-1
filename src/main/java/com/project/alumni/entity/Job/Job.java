package com.project.alumni.entity.Job;

import java.util.List;

import com.project.alumni.entity.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "jobs")
@Getter
@Setter
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
    //private List<String> requirements;

    @OneToOne
    private User user;

    @OneToOne(mappedBy = "job")
    private Posting jobPosting;

    @OneToMany(mappedBy = "job")
    private List<Application> jobApplications;
}
