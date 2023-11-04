package com.miu.alumnimanagementportal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class EventEntity extends BaseEntity{

    private String title;
    private String description;
    private EventType eventType;
    private String location;
    private Date staringDate;
    private LocalDateTime endingDate;
    /*@OneToMany
    List<Attendants>  attendants;*/
}
