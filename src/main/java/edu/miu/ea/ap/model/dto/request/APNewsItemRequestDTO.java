package edu.miu.ea.ap.model.dto.request;

import lombok.Data;

@Data
public class APNewsItemRequestDTO extends APRequestDTO {

    private String title;
    private String content;
    private String type;

    private APUserRequestDTO publisher;

}
