package edu.miu.ea.ap.model.dto.response;

import lombok.Data;

@Data
public class APSurveyResponseResponseDTO extends APResponseDTO {

    private String text;

    private APSurveyResponseDTO survey;

}
