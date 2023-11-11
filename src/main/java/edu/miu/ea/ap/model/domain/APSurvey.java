package edu.miu.ea.ap.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "AP_SURVEYS")
public class APSurvey extends APBaseEntityWithIdAuto {

    private String title;
    private String description;

    @OneToMany(mappedBy = "survey")
    private Set<APSurveyResponse> responses;

}