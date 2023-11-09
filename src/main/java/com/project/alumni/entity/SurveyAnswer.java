package com.project.alumni.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class SurveyAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;
    private int deleted = 0;

    @ManyToOne
    private Survey survey;

    @OneToOne
    private User user;
}
