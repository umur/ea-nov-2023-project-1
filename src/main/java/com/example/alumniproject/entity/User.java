package com.example.alumniproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class User extends BaseEntity {

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private int failedLoginAttempts;

    private LocalDateTime lastFailedLoginTimestamp;

    private Boolean accountLocked;

    private String token;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Profile profile;

    private String role;
}
