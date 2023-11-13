package edu.miu.ea.ap.model.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class APFileRequestDTO extends APRequestDTO {

    private String name;
    private String type;
    private String path;

}
