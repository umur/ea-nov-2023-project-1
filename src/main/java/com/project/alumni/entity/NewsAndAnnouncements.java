package com.project.alumni.entity;


import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "news_and_announcements")
public class NewsAndAnnouncements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;

    @PrePersist
    protected void onCreate() {
        // Set the default publishDate to the current date and time when a new entity is created without date
        if (publishDate == null)
            publishDate = new Date();
    }
}
