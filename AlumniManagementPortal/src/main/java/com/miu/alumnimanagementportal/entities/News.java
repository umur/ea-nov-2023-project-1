package com.miu.alumnimanagementportal.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class News extends BaseEntity{
    private String title;
    private NewsType newsType;
    private String description;

}
