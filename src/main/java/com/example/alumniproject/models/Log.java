package com.example.alumniproject.models;

import java.time.LocalDateTime;

import com.example.alumniproject.entity.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;
    private String method;
    private String url;
}
