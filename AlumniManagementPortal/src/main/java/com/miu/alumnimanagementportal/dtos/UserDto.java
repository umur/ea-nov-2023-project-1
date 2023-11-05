package com.miu.alumnimanagementportal.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    String firstName;
    String lastName;
    String email;
    String password;
    @NotNull
    @Size(message = "This is mandatory", min = 1, max = 8)
    Set<RoleDto> roles;
    @NotNull(message = "This is mandatory")
    ProfileDto profile;
}