package com.miu.alumnimanagementportal.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
public class Profile extends BaseEntity {
    @OneToOne(mappedBy = "profile", optional = false, orphanRemoval = true)
    private User user;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    private String phone;
    private String profileImage;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<WorkExperience> workExperiences = new LinkedHashSet<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProfessionalAchievement> professionalAchievements = new LinkedHashSet<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EducationDetails> educationDetails = new LinkedHashSet<>();

}
