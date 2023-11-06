package com.miu.alumnimanagementportal.entities;

import com.miu.alumnimanagementportal.common.enums.JobType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class JobPost extends BaseEntity {

    private String title;
    private String description;


    private JobType jobType;

    //owner of the job
    @OneToOne
    private User owner;

    private boolean isPublished = false;

    //status of the job - open or closed
    private PostStatus status;

    private String location;
    private String companyName;
    private String city;
    private String state;


}
