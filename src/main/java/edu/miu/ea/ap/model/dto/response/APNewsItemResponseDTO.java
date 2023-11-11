package edu.miu.ea.ap.model.dto.response;

import lombok.Data;

@Data
public class APNewsItemResponseDTO extends APResponseDTO {

    private String title;
    private String content;
    private String type;

    private APUserResponseDTO publisher;

}
