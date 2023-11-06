package com.ea.project.respository;

import com.ea.project.entity.ActivityLog;
import org.springframework.data.repository.ListCrudRepository;

public interface ActivityLogRepository extends ListCrudRepository<ActivityLog, Long> {
}
