package com.alumni.userservice.service.impl;

import com.alumni.userservice.entity.AuditAction;
import com.alumni.userservice.entity.AuditLog;
import com.alumni.userservice.repository.AuditLogRepository;
import com.alumni.userservice.service.AuditLogService;
import org.springframework.stereotype.Service;


@Service
public class AuditLogServiceImpl implements AuditLogService {
    private final AuditLogRepository auditLogRepo;

    public AuditLogServiceImpl(AuditLogRepository auditLogRepo) {
        this.auditLogRepo = auditLogRepo;
    }

    public void save(String actor, AuditAction auditAction){

        AuditLog auditLog = AuditLog.builder()
                .actor(actor)
                .auditAction(auditAction)
                .build();

        auditLogRepo.save(auditLog);
    }
}
