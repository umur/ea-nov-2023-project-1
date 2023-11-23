package com.example.EA_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class Address {

    private String city;
    private String state;
    private String zip;


}
