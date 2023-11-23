package com.example.surveyservice.dto;

import com.example.surveyservice.entity.Answer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionAnswer {
    private int questionId;
    private Answer answer;

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "questionId=" + questionId +
                ", answer=" + answer +
                '}';
    }
}
