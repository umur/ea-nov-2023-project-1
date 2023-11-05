package com.miu.alumnimanagementportal.services;


import com.miu.alumnimanagementportal.dtos.ProfileDto;
import com.miu.alumnimanagementportal.dtos.ProfileDto;

import java.util.List;

public interface ProfileService {
    void create(ProfileDto profileDto);

    List<ProfileDto> findAll();

    ProfileDto update(ProfileDto profileDto, Long id);

    ProfileDto getProfileById(Long id);

    void delete(Long id);
}
