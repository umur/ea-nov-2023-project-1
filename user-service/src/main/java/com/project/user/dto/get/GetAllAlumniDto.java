package com.project.user.dto.get;

import lombok.Data;

import java.util.List;

@Data
public class GetAllAlumniDto {
    private Long id;
    private String mail;
    private String phone;
    private String profilePicture;
    private String location;
    private String roleName;
}
