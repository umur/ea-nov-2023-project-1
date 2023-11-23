package com.project.user.dto.put;

import lombok.Data;

@Data
public class PutAddressDto {
    private String id;
    private String city;
    private String state;
    private String zip;
}
