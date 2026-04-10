package com.pm.billingservice.exception;

public class AppointmentReferenceAlreadyExistsException extends RuntimeException {

  public AppointmentReferenceAlreadyExistsException(String message) {
    super(message);
  }
}
