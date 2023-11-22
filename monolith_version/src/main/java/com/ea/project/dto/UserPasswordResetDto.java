package com.ea.project.dto;

import lombok.Data;

@Data
public class UserPasswordResetDto {
    private Long id;
    private String password;
    private String newPassword;
    private String confirmPassword;
}
