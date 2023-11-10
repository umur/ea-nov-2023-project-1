package com.ea.project.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
public class Announcement extends Information{
    private LocalDateTime endDateTime;
    private String isUrgent;
}
