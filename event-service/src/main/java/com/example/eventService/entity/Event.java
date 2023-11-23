package com.example.eventService.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String eventName;
    private String sponsor;
    private LocalDateTime dateTime;

    private String description;
    private String type;

    @ManyToMany
    @JoinTable(
            name = "event_person",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> participants = new HashSet<>();

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", participants=" + participants +
                '}';
    }
}
