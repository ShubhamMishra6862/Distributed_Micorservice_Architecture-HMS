package com.pm.billingservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public class BillRequestDTO {

  @NotNull(message = "Patient ID is required")
  private UUID patientId;

  @NotNull(message = "Appointment ID is required")
  private UUID appointmentId;

  @NotNull(message = "Amount is required")
  @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
  private BigDecimal amount;

  @NotBlank(message = "Description is required")
  private String description;

  @NotBlank(message = "Status is required")
  private String status;

  public UUID getPatientId() {
    return patientId;
  }

  public void setPatientId(UUID patientId) {
    this.patientId = patientId;
  }

  public UUID getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(UUID appointmentId) {
    this.appointmentId = appointmentId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
