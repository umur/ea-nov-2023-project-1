package com.project.alumni.entity.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;


import com.project.alumni.entity.Job.Job;
import com.project.alumni.entity.Job.Posting;
import com.project.alumni.entity.Event.Event;
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
    @Column(name = " email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "graduation_year")
    private String gradYear;
    private String industry;
    @Column(name = "educational_Details")
    private String educationalDetails;
    @Column(name = "professional_achievements")
    private String professionalAchievements;
    @Column(name = "profile_pic")
    private String profilePic;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @JsonBackReference
    private Address address;

    @ManyToMany
    @JoinTable(
            name = "courses_users",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    private Set<Course> courses;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    @OneToOne(mappedBy = "user")
    private Job job;

    @OneToMany(mappedBy = "user")
    private List<Application> jobApplications;

    @OneToMany(mappedBy = "poster")
    private List<Posting> jobPostings;


    @ManyToMany
    @JoinTable(
            name = "user_organizing_event",
            joinColumns = @JoinColumn(name = "organizerId"),
            inverseJoinColumns = @JoinColumn(name = "eventId")
    )
    private List<Event> organized;
    @ManyToMany
    @JoinTable(
            name = "user_attending_event",
            joinColumns = @JoinColumn(name = "attendeeId"),
            inverseJoinColumns = @JoinColumn(name = "eventId")
    )
    private List<Event> attended;
    @ManyToMany
    @JoinTable(
            name = "user_rsvped_event",
            joinColumns = @JoinColumn(name = "rsvperId"),
            inverseJoinColumns = @JoinColumn(name = "eventId")
    )
    private List<Event> rsvped;

}
