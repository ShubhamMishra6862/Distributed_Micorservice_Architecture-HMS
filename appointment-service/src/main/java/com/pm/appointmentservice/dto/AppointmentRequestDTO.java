package com.pm.appointmentservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class AppointmentRequestDTO {

  @NotNull(message = "Patient ID is required")
  private UUID patientId;

  @NotBlank(message = "Details are required")
  private String details;

  // Getters and setters

  public UUID getPatientId() {
    return patientId;
  }

  public void setPatientId(UUID patientId) {
    this.patientId = patientId;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }
}
