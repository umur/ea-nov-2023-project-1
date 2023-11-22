package com.alumni.userservice.service;

import com.alumni.userservice.entity.AuditAction;
import com.alumni.userservice.entity.AuditLog;
import com.alumni.userservice.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuditLogService {
    private final AuditLogRepository auditLogRepository;

    public void save(String actor, AuditAction auditAction){

        AuditLog auditLog = AuditLog.builder()
                .actor(actor)
                .auditAction(auditAction)
                .build();

        auditLogRepository.save(auditLog);
    }
}
