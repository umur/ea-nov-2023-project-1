package com.ea.project.respository;

import com.ea.project.entity.Experience;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepo extends ListCrudRepository<Experience, Long> {

}
