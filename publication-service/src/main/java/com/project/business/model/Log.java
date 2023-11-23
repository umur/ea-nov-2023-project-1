package com.project.business.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private String operation;
    private long duration;

    public Log(LocalDateTime date, String operation, long duration) {
        this.date = date;
        this.operation = operation;
        this.duration = duration;
    }
}
