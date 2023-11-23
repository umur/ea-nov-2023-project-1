package com.alumni.userservice.repository;
import com.alumni.userservice.entity.AuditLog;
import org.springframework.data.repository.ListCrudRepository;



public interface AuditLogRepository extends ListCrudRepository<AuditLog, Long> {
}
