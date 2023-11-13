package edu.miu.ea.ap.model.dto.request;

import lombok.Data;

@Data
public class APSurveyResponseRequestDTO extends APRequestDTO {

    private String text;

    private APSurveyRequestDTO survey;

}
