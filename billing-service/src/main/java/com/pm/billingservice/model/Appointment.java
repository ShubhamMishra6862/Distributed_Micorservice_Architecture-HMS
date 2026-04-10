package com.pm.billingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appointments")
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private UUID appointmentId;

  private UUID patientId;

  private String eventType;

  private LocalDateTime receivedAt;

  // Constructors, getters, setters

  public Appointment() {}

  public Appointment(UUID appointmentId, UUID patientId, String eventType) {
    this.appointmentId = appointmentId;
    this.patientId = patientId;
    this.eventType = eventType;
    this.receivedAt = LocalDateTime.now();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(UUID appointmentId) {
    this.appointmentId = appointmentId;
  }

  public UUID getPatientId() {
    return patientId;
  }

  public void setPatientId(UUID patientId) {
    this.patientId = patientId;
  }

  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  public LocalDateTime getReceivedAt() {
    return receivedAt;
  }

  public void setReceivedAt(LocalDateTime receivedAt) {
    this.receivedAt = receivedAt;
  }
}
