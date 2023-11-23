package com.project.business.dto.post;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PostAddressDto implements Serializable {
    private String street;
    private String city;
    private String state;
    private String zip;

}
