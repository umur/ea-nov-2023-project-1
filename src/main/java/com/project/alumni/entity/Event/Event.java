package com.project.alumni.entity.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.project.alumni.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "events")
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    private LocalDateTime eventDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private EventType type;

    @ManyToMany(mappedBy = "organized")
    private List<User> organizers = new ArrayList<>();
    @ManyToMany(mappedBy = "attended")
    private List<User> attendees;
    @ManyToMany(mappedBy = "rsvped")
    private List<User> rsvpers;

}
