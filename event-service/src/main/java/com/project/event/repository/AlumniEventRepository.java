package com.project.event.repository;

import com.project.event.model.AlumniEvent;
import com.project.event.model.id.AlumniEventId;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumniEventRepository extends ListCrudRepository<AlumniEvent, Long> {
    AlumniEvent findById(AlumniEventId alumniEventId);
}



