package com.example.aopassignment.repository;

import com.example.aopassignment.model.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepository  extends JpaRepository<ActivityLog,Long> {
}