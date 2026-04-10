package com.pm.billingservice.dto;

import java.util.UUID;

public class AppointmentValidationDTO {

  private UUID appointmentId;
  private UUID patientId;
  private boolean exists;

  // Constructors, getters, setters

  public AppointmentValidationDTO() {}

  public AppointmentValidationDTO(UUID appointmentId, UUID patientId, boolean exists) {
    this.appointmentId = appointmentId;
    this.patientId = patientId;
    this.exists = exists;
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

  public boolean isExists() {
    return exists;
  }

  public void setExists(boolean exists) {
    this.exists = exists;
  }
}
