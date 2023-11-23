package com.project.business.dto.get;

import lombok.Data;

@Data
public class GetCompanyDto {
    private String id;
    private String name;
    private String industry;
    private GetAddressDto address;
}
