package com.miu.alumnimanagementportal.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Survey extends BaseEntity {
    private String title;
    private String description;
    private String feedback;
    private String url;


}
