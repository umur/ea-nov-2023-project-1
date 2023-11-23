package com.alumni.jobservice.Service;

import com.alumni.jobservice.Dto.ExperienceDTO;
import com.alumni.jobservice.Entity.Experience;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExperienceService {
    Experience addExperience(ExperienceDTO experience);

    ResponseEntity<?> deleteExperience(Long id);

    List<Experience> getExperiencesByProfileId(Long id);

    ResponseEntity<?> editExperience(Long id, ExperienceDTO experience);
}
