package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.dtos.ProfileDto;
import com.miu.alumnimanagementportal.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {
    @Override
    public void create(ProfileDto profileDto) {

    }

    @Override
    public List<ProfileDto> findAll() {
        return null;
    }

    @Override
    public ProfileDto update(ProfileDto profileDto, Long id) {
        return null;
    }

    @Override
    public ProfileDto getProfileById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
