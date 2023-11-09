package com.ea.project.respository;

import com.ea.project.entity.Job;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends ListCrudRepository<Job , Long> {
}
