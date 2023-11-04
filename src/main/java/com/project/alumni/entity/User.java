package com.project.alumni.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = " email", unique = true)
    private String email;
    @Column (name = "password", nullable = false)
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

}
