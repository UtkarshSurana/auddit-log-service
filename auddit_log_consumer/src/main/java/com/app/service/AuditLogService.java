package com.app.service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AuditLogRepository;
import com.app.dto.AuditLogMessage;
import com.app.entity.AuditLog;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @RabbitListener(queues = "audit-queue")
    public void handleAuditLogMessage(AuditLogMessage message) {
        AuditLog log = new AuditLog(message.getOperation(), message.getDetails(), message.getTimestamp());
        auditLogRepository.save(log);
    }
}
