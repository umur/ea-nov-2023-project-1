package com.alumni.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "audit_logs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String actor;
    @Enumerated(EnumType.STRING)
    private AuditAction auditAction;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;

    @PrePersist
    public void prePersist(){
        createdOn = LocalDateTime.now();
        modifiedOn = LocalDateTime.now();
    }
}
