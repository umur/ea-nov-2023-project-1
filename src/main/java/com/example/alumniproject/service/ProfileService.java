package com.example.alumniproject.service;

import java.util.List;
import java.util.Optional;

import com.example.alumniproject.entity.Profile;

public interface ProfileService {
    Profile save(Profile profile);

    List<Profile> findAll();

    Optional<Profile> findById(Long id);

    Profile update(Long id, Profile profile);

    void deleteById(Long id);
}
