package com.ea.project.service.impl;
import com.ea.project.entity.Experience;
import com.ea.project.respository.ExperienceRepo;
import com.ea.project.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepo experienceRepo;

    @Override
    public Experience create(Experience experience) {
        return experienceRepo.save(experience);
    }

    @Override
    public List<Experience> findAll() {
        return experienceRepo.findAll();
    }

    @Override
    public void update(Experience experience) {

    }

    @Override
    public Experience getExperience(Long id) {
        return experienceRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void delete(Experience experience) {

    }
}
