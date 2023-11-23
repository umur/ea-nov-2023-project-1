package com.project.user.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetAlumniAuth {
    private String mail;
    private String password;
    private Long roleId;

}
