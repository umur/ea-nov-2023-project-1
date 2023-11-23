package com.project.business.dto.post;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostOpenJobDto implements Serializable {
    private String description;
    private LocalDateTime openDate;
    private Long companyId;
    private List<Long> applicantIds;
}
