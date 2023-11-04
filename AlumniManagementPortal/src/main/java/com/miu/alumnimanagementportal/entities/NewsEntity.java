package com.miu.alumnimanagementportal.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NewsEntity extends BaseEntity{
    private String title;
    private NewsType newsType;
    private String description;

}
