package com.alumni.userservice.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
