package com.project.alumni.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {
    private Long id;
    // email should not be null or empty
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;
    // Password should not be null or empty
    @NotEmpty
    private String password;
}
