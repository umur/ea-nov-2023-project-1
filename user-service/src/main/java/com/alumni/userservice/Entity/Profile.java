package com.alumni.userservice.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Getter
@Setter
public class Profile extends BaseEntity {

    private String phoneNumber;
    private String major;

    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "profile_id")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Education> education;

    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "profile_id")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Achievement> achievements;

    private String profileImage;

    private String country;
    private String state;
    private String street;
    private int zip;
    private String city;

}
