package com.project.user.dto.get;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetFullAlumniDto {
    private Long id;
    private String mail;
    private String password;
    private String phone;
    private String profilePicture;
    private String location;
    private String roleName;
    private List<GetProfExperienceDto> profExperiences;
}
