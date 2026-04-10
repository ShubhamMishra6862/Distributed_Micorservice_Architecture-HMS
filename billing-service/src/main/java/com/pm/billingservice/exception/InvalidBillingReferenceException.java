package com.pm.billingservice.exception;

public class InvalidBillingReferenceException extends RuntimeException {

  public InvalidBillingReferenceException(String message) {
    super(message);
  }
}
