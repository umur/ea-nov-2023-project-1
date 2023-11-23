package com.project.event.model.base;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public abstract class TimeTrack extends BaseClass {
    private LocalDate startDate;
    private LocalDate endDate;

}
