package com.project.business.model;

import com.project.business.model.id.ApplicationId;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Application {
    @EmbeddedId
    private ApplicationId id;

    @ManyToOne
    @MapsId("openJobId")
    private OpenJob openJob;
//    @MapsId("alumniId")
}

