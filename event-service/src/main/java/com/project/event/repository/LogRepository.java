package com.project.event.repository;

import com.project.event.model.Log;
import org.springframework.data.repository.ListCrudRepository;

public interface LogRepository extends ListCrudRepository<Log,Long> {
}
