package com.event.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Event {
    @Id
    private int id;
    private String eventName;
    private String sponsor;

    private String description;
    private String type;

    @OneToMany(mappedBy = "event")
    private List<Person> attendance;
}
