package com.example.surveyservice.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class FeedbackKey implements Serializable {
    private int surveyId;
    private int questionId;
    private int answerId;
}
