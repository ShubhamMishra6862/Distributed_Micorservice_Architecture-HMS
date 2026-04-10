package com.pm.appointmentservice.dto;

import com.pm.appointmentservice.model.AppointmentStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateStatusDTO {

  @NotNull(message = "Status is required")
  private AppointmentStatus status;

  // Getters and setters

  public AppointmentStatus getStatus() {
    return status;
  }

  public void setStatus(AppointmentStatus status) {
    this.status = status;
  }
}
