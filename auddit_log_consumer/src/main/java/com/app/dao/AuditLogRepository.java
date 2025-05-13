package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.AuditLog;


public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
