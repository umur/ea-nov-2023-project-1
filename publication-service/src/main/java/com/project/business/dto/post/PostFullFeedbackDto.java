package com.project.business.dto.post;

import com.project.business.model.Alumni;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostFullFeedbackDto {
    private String description;
    private Alumni alumni;
}
