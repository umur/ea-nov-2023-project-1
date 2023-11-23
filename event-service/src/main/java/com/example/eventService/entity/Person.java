package com.example.eventService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String personName;

    @ManyToMany(mappedBy = "participants")
    private Set<Event> events = new HashSet<>();
}
