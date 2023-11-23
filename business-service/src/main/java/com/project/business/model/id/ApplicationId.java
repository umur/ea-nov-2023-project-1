package com.project.business.model.id;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class ApplicationId implements Serializable {
    private Long openJobId;

    private Long  alumniId;
}
