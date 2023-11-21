package main.java.com.alumni.insight.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Insights {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date publicationDate;
    private int deleted = 0;

    @PrePersist
    protected void onCreate() {
        // Set the default publishDate to the current date and time when a new entity is created without date
        if (publicationDate == null)
            publicationDate = new Date();
    }
}
