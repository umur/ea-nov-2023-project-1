package com.project.business.dto.put;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PutFullAlumniDto {

    private Long id;
    private String mail;
    private String password;
    private String phone;
    private String profilePicture;
    private String location;
    private String roleName;
    private List<PutProfExperienceDto> profExperiences;
}
