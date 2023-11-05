package com.project.alumni.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import com.project.alumni.entity.Job.Job;
import com.project.alumni.entity.Job.Application;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
<<<<<<< HEAD
@Table(name="users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "email_unique",
                        columnNames = "email"
                )
        }
)
=======
@Table(name = "users")
>>>>>>> 49fa70425b0d6f68bc9a1c4d3d4f3c9d286700bd
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = " email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "graduation_year")
    private LocalDateTime graduationYear;
    private String industry;
    @Column(name = "educational_Details")
    private String educationalDetails;
    @Column(name = "professional_achievements")
    private String professionalAchievements;
    @Column(name = "profile_pic")
    private String profilePic;

<<<<<<< HEAD
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Address address;

=======
    @OneToOne(mappedBy = "user")
    private Job job;

    @OneToMany(mappedBy = "user")
    private List<Application> jobApplications;
>>>>>>> 49fa70425b0d6f68bc9a1c4d3d4f3c9d286700bd
}
