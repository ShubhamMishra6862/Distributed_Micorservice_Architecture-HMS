package com.pm.billingservice.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class BillingAppointmentRequestDTO {

  @NotNull(message = "Appointment ID is required")
  private UUID appointmentId;

  @NotNull(message = "Patient ID is required")
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
