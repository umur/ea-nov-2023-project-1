package com.alumni.jobservice.Entity;

//import com.fasterxml.jackson.annotation.JsonBackReference;

import com.alumni.jobservice.Dto.JobDTO;
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

    public Job(JobDTO jobDTO){
        this.posterId = jobDTO.getPosterId();
        this.title = jobDTO.getTitle();
        this.description = jobDTO.getDescription();
        this.organization = jobDTO.getOrganization();
        this.assignerId = jobDTO.getAssignerId();
        this.location = new Location(jobDTO.getLocation());
    }
}
