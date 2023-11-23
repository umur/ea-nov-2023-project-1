package com.project.event.model;
import com.project.event.model.base.TimeTrack;
import com.project.event.model.type.EventType;
import lombok.Getter;import lombok.Setter;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
public class Event extends TimeTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_type_id")
    private EventType eventType;

    private String description;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<AlumniEvent> alumniEvents;

}
