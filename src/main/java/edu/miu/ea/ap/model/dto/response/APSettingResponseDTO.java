package edu.miu.ea.ap.model.dto.response;

import lombok.Data;

@Data
public class APSettingResponseDTO extends APResponseDTO {
    private String key;
    private String value;

}
