package com.miu.alumnimanagementportal.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
public class Event extends BaseEntity{

    private String title;
    private String description;
    private EventType eventType;
    private String location;
    private Date staringDate;
    private LocalDateTime endingDate;

    @ManyToMany
    @JoinTable(name = "Event_users",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    private Set<User> organizers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "event", orphanRemoval = true)
    private Set<Attendant> attendants = new LinkedHashSet<>();

}
