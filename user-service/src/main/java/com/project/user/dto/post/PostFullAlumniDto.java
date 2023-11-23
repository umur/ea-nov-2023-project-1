package com.project.user.dto.post;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PostFullAlumniDto implements Serializable {
    private String mail;
    private String password;
    private String phone;
    private String profilePicture;
    private String location;
    private Long roleId;
}
