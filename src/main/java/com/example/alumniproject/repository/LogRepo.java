package com.example.alumniproject.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.alumniproject.models.Log;

@Repository
public interface LogRepo extends ListCrudRepository<Log, Long> {

}
