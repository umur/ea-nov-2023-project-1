package com.project.business.repository;

import com.project.business.model.Log;
import org.springframework.data.repository.ListCrudRepository;

public interface LogRepository extends ListCrudRepository<Log,Long> {
}
