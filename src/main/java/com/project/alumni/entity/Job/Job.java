package com.project.alumni.entity.Job;

import java.util.List;

import com.project.alumni.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

    private String requirements;

    @OneToOne
    private User user;

    @OneToOne(mappedBy = "job")
    private Posting jobPosting;

    @OneToMany(mappedBy = "job")
    private List<Application> jobApplications;
}
