package com.miu.alumnimanagementportal.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class User extends BaseEntity{

    @Column(name = "firstname", nullable = false)
    private String firstName;
    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(unique=true)
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_id", unique = true)
    private Profile profile;


//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Profile profile;

}
