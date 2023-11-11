package edu.miu.ea.ap.model.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "AP_SURVEY_RESPONSES")
public class APSurveyResponse extends APBaseEntityWithIdAuto {

    private String text;

    @ManyToOne
    @JoinColumn(name = "SURVEY_ID")
    private APSurvey survey;

}