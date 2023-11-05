package com.miu.alumnimanagementportal.dtos;

import jakarta.validation.constraints.*;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * DTO for {@link com.miu.alumnimanagementportal.entities.User}
 */
@Value
public class UserDto implements Serializable {
    Long id;
    Long version;
    Date createdDate;
    Date lastModifiedDate;
    @NotNull
    @NotEmpty
    @NotBlank
    String firstName;
    @NotNull
    @NotEmpty
    @NotBlank
    String lastName;
    @NotNull
    @NotEmpty
    @NotBlank
    @Email
    String email;
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(message = "Password length should be between 5 and 10", min = 5, max = 10)
    String password;

    Set<RoleDto> roles;

    ProfileDto profile;
}