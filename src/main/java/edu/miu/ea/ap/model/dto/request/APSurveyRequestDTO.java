package edu.miu.ea.ap.model.dto.request;

import lombok.Data;

@Data
public class APSurveyRequestDTO extends APRequestDTO {

    private String title;
    private String description;

}
