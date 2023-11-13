package edu.miu.ea.ap.model.dto;

import lombok.Data;

@Data
public class APAddressDTO extends APDtoModel {

    private String locationStreet;
    private String locationCity;
    private String locationState;
    private String locationPostalCode;
    private String locationCountry;

}
