package com.pm.billingservice.dto;

import java.util.UUID;

public class BillingAppointmentResponseDTO {

  private UUID appointmentId;
  private UUID patientId;

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
}
