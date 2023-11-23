package com.example.surveyservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeedbackDto {
    private int surveyId;
    private List<QuestionAnswer> questionAnswers;

    @Override
    public String toString() {
        return "FeedbackDto{" +
                "surveyId=" + surveyId +
                ", questionAnswers=" + questionAnswers +
                '}';
    }
}
