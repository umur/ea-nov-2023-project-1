package edu.miu.ea.ap.model.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class APJobRequestDTO extends APRequestDTO {

    private String description;
    private APCompanyRequestDTO company;

    private APUserRequestDTO advertiser;

    private List<APUserRequestDTO> applicants;

}
