package com.example.surveyservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Feedback {
    @EmbeddedId
    private FeedbackKey feedbackKey;

    @OneToOne
    @MapsId("surveyId")
    private Survey survey;

    @OneToOne
    @MapsId("questionId")
    private Question question;

    @OneToOne
    @MapsId("answerId")
    private Answer answer;

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackKey=" + feedbackKey +
                ", survey=" + survey +
                ", question=" + question +
                ", answer=" + answer +
                '}';
    }
}
