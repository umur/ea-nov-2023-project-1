package com.project.alumni.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

import com.project.alumni.entity.Job.Job;
import com.project.alumni.entity.Job.Posting;
import com.project.alumni.entity.Job.Application;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "email_unique", columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = " email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "graduation_year")
    private String graduationYear;
    private String industry;
    @Column(name = "educational_Details")
    private String educationalDetails;
    @Column(name = "professional_achievements")
    private String professionalAchievements;
    @Column(name = "profile_pic")
    private String profilePic;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Address address;

    @OneToOne(mappedBy = "user")
    private Job job;

    @OneToMany(mappedBy = "user")
    private List<Application> jobApplications;

    @OneToMany(mappedBy = "poster")
    private List<Posting> jobPostings;
    
}
