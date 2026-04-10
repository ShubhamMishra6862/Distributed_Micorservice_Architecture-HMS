package com.pm.appointmentservice.exception;

public class PatientValidationException extends RuntimeException {

  public PatientValidationException(String message) {
    super(message);
  }
}
