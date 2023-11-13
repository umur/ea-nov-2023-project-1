package edu.miu.ea.ap.model.dto.request;

import lombok.Data;

@Data
public class APSettingRequestDTO extends APRequestDTO {

    private String key;
    private String value;

}
