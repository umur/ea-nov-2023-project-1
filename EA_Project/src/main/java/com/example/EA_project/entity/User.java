package com.example.EA_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String password;
    private String email;


    @Embedded
    private Address address;

    private String major;
    private boolean isActive;
    private String cv;
    private boolean isDeleted;
    @Enumerated(EnumType.STRING)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Log> logs;


}
