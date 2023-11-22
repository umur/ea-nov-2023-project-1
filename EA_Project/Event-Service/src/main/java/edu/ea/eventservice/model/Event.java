package edu.ea.eventservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String date;
    private String description;
    private String category;

    private long userId;

    @ElementCollection(fetch = FetchType.EAGER)
    List<Long> RSVP;



}

