package com.example.surveyservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String surveyName;

    @OneToMany(mappedBy = "survey",cascade = CascadeType.ALL)
    private List<Question> questions;

    @OneToMany(mappedBy = "survey",cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", questions=" + questions +
                '}';
    }
}
