package com.app.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class AuditLogMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    private String operation;
    private String details;
    private LocalDateTime timestamp;
    
	public AuditLogMessage(String operation, String details, LocalDateTime timestamp) {
		super();
		this.operation = operation;
		this.details = details;
		this.timestamp = timestamp;
	}
}
