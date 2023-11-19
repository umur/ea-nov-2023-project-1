package com.alumni.EventService.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "events")
@Data
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

    @OneToMany(mappedBy = "event")
    private List<UsersOrganizingEvents> organizers = new ArrayList<>();

    @OneToMany(mappedBy = "event")
    private List<UsersAttendingEvents> attendees = new ArrayList<>();

    @OneToMany(mappedBy = "event")
    private List<UsersRsvpingEvents> rsvpers = new ArrayList<>();

}
