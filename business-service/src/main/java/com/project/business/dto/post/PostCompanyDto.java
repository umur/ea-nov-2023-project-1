package com.project.business.dto.post;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PostCompanyDto implements Serializable {
    private String name;
    private String industry;
    private PostAddressDto address;

}
