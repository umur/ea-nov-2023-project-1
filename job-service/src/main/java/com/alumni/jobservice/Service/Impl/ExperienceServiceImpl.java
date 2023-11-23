package com.alumni.jobservice.Service.Impl;

import com.alumni.jobservice.Dto.ExperienceDTO;
import com.alumni.jobservice.Entity.Experience;
import com.alumni.jobservice.Entity.Job;
import com.alumni.jobservice.Repo.ExperienceRepo;
import com.alumni.jobservice.Repo.JobRepo;
import com.alumni.jobservice.Service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {
    private final ExperienceRepo repo;
    private final JobRepo jobRepo;

    @Override
    public Experience addExperience(ExperienceDTO experience) {
        Experience exp = new Experience(experience);
        Optional<Job> optJob = jobRepo.findById(experience.getJobId());
        if (optJob.isPresent()) {
            exp.setJob(optJob.get());
        }
        return repo.save(exp);
    }

    @Override
    public ResponseEntity<?> deleteExperience(Long id) {
        Optional<Experience> optionalExperience = repo.findById(id);
        if(optionalExperience.isPresent()){
            optionalExperience.get().setDeleteYn(true);
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public List<Experience> getExperiencesByProfileId(Long id) {
        return repo.findExperienceByProfileId(id);
    }

    @Override
    public ResponseEntity<?> editExperience(Long id, ExperienceDTO experience) {
        Optional<Experience> optionalExperience = repo.findById(id);
        Optional<Job> optionalJob = jobRepo.findById(experience.getJobId());
        if(optionalExperience.isPresent() && optionalJob.isPresent()){
            Experience editedExperience = optionalExperience.get();
            editedExperience.setJob(optionalJob.get());
            editedExperience.setStartDate(experience.getStartDate());
            editedExperience.setEndDate(experience.getEndDate());
            editedExperience.setProfileId(experience.getProfileId());
            repo.save(editedExperience);
            return ResponseEntity.ok(repo.save(editedExperience));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
