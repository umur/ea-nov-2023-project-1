package com.alumni.jobservice.Entity;

import com.alumni.jobservice.Dto.ExperienceDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Experience extends BaseEntity{
    private Date startDate;
    private Date endDate;
    @OneToOne
    private Job job;
    private Long profileId;

    public Experience(ExperienceDTO experience) {
        super();
        this.startDate = experience.getStartDate();
        this.endDate = experience.getEndDate();
        this.profileId = experience.getProfileId();
    }
}
