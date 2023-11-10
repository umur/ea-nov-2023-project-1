package com.miu.alumnimanagementportal.repositories;

import com.miu.alumnimanagementportal.entities.common.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
}