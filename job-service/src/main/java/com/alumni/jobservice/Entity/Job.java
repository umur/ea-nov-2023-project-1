package com.alumni.jobservice.Entity;

//import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Job extends BaseEntity {

    private Long posterId;

    private String title;

    private String description;

    private String organization;

    private Long assignerId;

    @OneToOne
    private Location location;
}
