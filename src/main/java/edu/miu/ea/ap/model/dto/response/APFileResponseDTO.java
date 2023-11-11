package edu.miu.ea.ap.model.dto.response;

import lombok.Data;

@Data
public class APFileResponseDTO extends APResponseDTO {

    private String name;
    private String type;
    private String path;

}
