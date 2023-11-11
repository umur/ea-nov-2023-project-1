package edu.miu.ea.ap.model.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class APJobResponseDTO extends APResponseDTO {

    private String description;
    private APCompanyResponseDTO company;

    private APUserResponseDTO advertiser;

    private List<APUserResponseDTO> applicants;

}
