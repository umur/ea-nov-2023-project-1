package com.alumni.jobservice.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {
    private String country;
    private String state;
    private String street;
    private int zip;
    private String city;
}
