package com.example.alumniproject.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.alumniproject.entity.Profile;

@Repository
public interface ProfileRepo extends ListCrudRepository<Profile, Long> {

}
