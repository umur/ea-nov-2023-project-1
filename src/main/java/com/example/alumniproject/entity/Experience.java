package com.example.alumniproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Experience extends BaseEntity{
    private Date startDate;
    private Date endDate;
    @OneToOne
    private Job job;
}
