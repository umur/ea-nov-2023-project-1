package edu.miu.ea.ap.model.dto;

import lombok.Data;

@Data
public class APPersonAddressDTO extends APDtoModel {

    private APAddressDTO home;
    private APAddressDTO work;

    private String homeLandAddress1;
    private String homeLandAddress2;
    private String homeLandAddress3;

}
