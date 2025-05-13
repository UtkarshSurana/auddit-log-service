# 📝 Audit Log Microservice

This microservice is part of a distributed system that facilitates audit logging using **RabbitMQ** as the message broker. It is designed to asynchronously receive audit-related data from other microservices and persist or process it accordingly.

## 📦 Overview

The system consists of two microservices:

1. **Producer Service**: Responsible for sending audit log messages (e.g., user actions, system events) to a RabbitMQ exchange.
2. **Audit Log Service (Consumer)**: Listens to the audit message queue, deserializes the message, and processes or stores it.

---

## 🔧 Technologies Used

- **Java 17+**
- **Spring Boot**
- **Spring AMQP (RabbitMQ)**
- **RabbitMQ**
- **Jackson (for JSON serialization/deserialization)**
- **Maven**

---

## 🔁 How It Works

1. The **Producer Service** sends a message to a **RabbitMQ topic exchange** (`audit-exchange`) using the **routing key** `audit-routing-key`.
2. The **Audit Log Service** is subscribed to a queue (`audit-queue`) bound to this exchange.
3. When a message arrives:
   - RabbitMQ delivers it to the `audit-queue`.
   - The Audit Log Service consumes it.
   - The message is converted to a Java object (`AuditLogMessage`) using a Jackson-based converter.
   - The log is processed (e.g., printed to console, saved to DB, etc.).

---

## 📥 Example Message Structure

```json
{
  "timestamp": "2025-05-13T12:34:56Z",
  "service": "user-service",
  "action": "USER_CREATED",
  "details": "User John Doe was created."
}

