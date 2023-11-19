package com.project.alumni.entity;

import com.project.alumni.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    private Date endDate;
    private int deleted = 0;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "survey")
    private List<SurveyAnswer> surveyAnswer;

    @PrePersist
    protected void onCreate() {
        // Set the default starDate to the current date and time when a new entity is created without date
        if (startDate == null)
            startDate = new Date();
    }
}
