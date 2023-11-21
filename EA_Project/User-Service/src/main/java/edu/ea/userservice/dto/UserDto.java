package edu.ea.userservice.dto;

import edu.ea.userservice.model.Role;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private boolean active;
    private boolean deleted;
    private RoleDto role;
}
