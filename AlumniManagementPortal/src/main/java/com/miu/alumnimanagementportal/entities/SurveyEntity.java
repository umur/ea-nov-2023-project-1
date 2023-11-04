package com.miu.alumnimanagementportal.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SurveyEntity extends BaseEntity {
    private String title;
    private String description;
    private String feedback;
    private String url;


}
