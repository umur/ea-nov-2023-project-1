package com.alumni.jobservice.Repo;

import com.alumni.jobservice.Entity.Location;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends ListCrudRepository<Location, Long> {
}
