package com.miu.alumnimanagementportal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.modelmapper.internal.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class ProfessionalAchievementsEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String title;
    private Date year;
    private String description;

    //Relation remaining


}
