package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.dao.DataRepository;
import com.app.dto.AuditLogMessage;
import com.app.entity.Data;

@Service
@Transactional
public class CrudService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private DataRepository dataRepository;

    
    public void createData(Data data) {
        dataRepository.save(data);
        sendAuditLog("CREATE", "Created new data with ID: " + data.getId());
    }


    public void updateData(Data data) {
        dataRepository.save(data);
        sendAuditLog("UPDATE", "Updated data with ID: " + data.getId());
    }


    public void deleteData(Long id) {
        dataRepository.deleteById(id);
        sendAuditLog("DELETE", "Deleted data with ID: " + id);
    }

    private void sendAuditLog(String operation, String details) {
        AuditLogMessage message = new AuditLogMessage(operation, details, LocalDateTime.now());
        rabbitTemplate.convertAndSend("audit-exchange", "audit-routing-key", message);
    }
}
