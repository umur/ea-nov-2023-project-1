package edu.miu.ea.ap.model.domain;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class APAddress {

    private String locationStreet;
    private String locationCity;
    private String locationState;
    private String locationPostalCode;
    private String locationCountry;

}
