package com.alumni.jobservice.Entity;

import com.alumni.jobservice.Dto.LocationDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String country;
    private String state;
    private String street;
    private int zip;
    private String city;

    public Location(LocationDTO location) {
        this.country = location.getCountry();
        this.state = location.getState();
        this.street = location.getStreet();
        this.zip = location.getZip();
        this.city = location.getCity();
    }
}
