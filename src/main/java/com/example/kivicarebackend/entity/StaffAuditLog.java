package com.example.kivicarebackend.entity;

import com.example.kivicarebackend.enums.staffs.AuditAction;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "staff_audit_logs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffAuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    private Long staffId;

    @Enumerated(EnumType.STRING)
    private AuditAction action; // CREATE, UPDATE, DELETE

    @Column(columnDefinition = "json")
    private String oldData;

    @Column(columnDefinition = "json")
    private String newData;

    private Long changedBy; // user_id thao tác

    private LocalDateTime changedAt;
}



