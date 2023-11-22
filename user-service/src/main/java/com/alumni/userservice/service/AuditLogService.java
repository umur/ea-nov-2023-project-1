package com.alumni.userservice.service;

import com.alumni.userservice.entity.AuditAction;


public interface AuditLogService {

    public void save(String actor, AuditAction auditAction);

}
