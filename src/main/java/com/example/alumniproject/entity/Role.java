package com.example.alumniproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;

//@Entity
//@Embeddable
@Getter
public enum Role {
    ADMIN,FACULTY,STUDENT;
}
