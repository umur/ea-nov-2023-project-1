package com.project.user.dto.post;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostCompanyDto implements Serializable {
    private String name;
    private String industry;
    private PostAddressDto address;
}
