package com.ea.project.respository;

import com.ea.project.entity.Faculty;
import org.springframework.data.repository.ListCrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepo extends ListCrudRepository<Faculty, Long> {

}
