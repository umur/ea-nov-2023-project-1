package com.project.user.dto.post;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostAddressDto implements Serializable {
    private String street;
    private String city;
    private String state;
    private String zip;

}
