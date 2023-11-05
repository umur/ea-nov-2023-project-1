package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.dtos.ProfessionalAchievementDto;
import com.miu.alumnimanagementportal.services.ProfessionalAchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfessionalAchievementServiceImpl implements ProfessionalAchievementService {
    @Override
    public void create(ProfessionalAchievementDto professionalAchievementDto) {

    }

    @Override
    public List<ProfessionalAchievementDto> findAll() {
        return null;
    }

    @Override
    public ProfessionalAchievementDto update(ProfessionalAchievementDto professionalAchievementDto, Long id) {
        return null;
    }

    @Override
    public ProfessionalAchievementDto getProfessionalAchievementById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
