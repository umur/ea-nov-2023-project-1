package edu.miu.ea.ap.model.dto;

import lombok.Data;

@Data
public class APAddressDTO extends APDtoModel {

    private String address;
    private Integer zone;
    private Integer street;
    private Integer building;
    private Integer unit;
    private Integer flat;
    private Integer room;

}
