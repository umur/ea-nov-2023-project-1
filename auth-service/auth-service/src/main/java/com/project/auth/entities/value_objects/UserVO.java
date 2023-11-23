package com.project.auth.entities.value_objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserVO {
    private String mail;
    private String password;
    private Long roleId;

}
