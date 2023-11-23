package com.project.event.model;

import com.project.event.model.base.BaseClass;
import com.project.event.model.id.AlumniEventId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
public class AlumniEvent extends BaseClass {
    @EmbeddedId
    private AlumniEventId id;

    @MapsId("eventId")
    @ManyToOne
    private Event event;

    private boolean isAttended;
    private boolean isAccepted;

}
