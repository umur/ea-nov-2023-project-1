package com.example.alumniproject.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.alumniproject.entity.Profile;
import com.example.alumniproject.repository.ProfileRepo;
import com.example.alumniproject.service.ProfileService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepo profileRepo;

    @Override
    public Profile save(Profile profile) {
        return profileRepo.save(profile);
    }

    @Override
    public List<Profile> findAll() {
        return profileRepo.findAll();
    }

    @Override
    public Optional<Profile> findById(Long id) {
        return profileRepo.findById(id);
    }

    @Override
    public Profile update(Long id, Profile profile) {
        Optional<Profile> toBeUpdated = profileRepo.findById(id);
        if (toBeUpdated.isPresent()) {
            Profile updated = toBeUpdated.get();
            updated.setAchievements(profile.getAchievements());
            updated.setEducation(profile.getEducation());
            updated.setJobs(profile.getJobs());
            updated.setCity(profile.getCity());
            updated.setCountry(profile.getCountry());
            updated.setState(profile.getState());
            updated.setStreet(profile.getStreet());
            updated.setZip(profile.getZip());
            updated.setMajor(profile.getMajor());
            updated.setPhoneNumber(profile.getPhoneNumber());
            updated.setProfileImage(profile.getProfileImage());
            updated.setModifiedDate(new Date());
            return profileRepo.save(updated);
        } else {
            throw new IllegalArgumentException("Profile ID NOT FOUND");
        }
    }

    @Override
    public void deleteById(Long id) {
        profileRepo.deleteById(id);
    }

}
