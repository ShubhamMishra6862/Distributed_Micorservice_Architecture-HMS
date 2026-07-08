package com.pm.appointmentservice.grpc;

import io.grpc.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import patient.validation.PatientValidationRequest;
import patient.validation.PatientValidationResponse;
import patient.validation.PatientValidationServiceGrpc;
import java.util.UUID;

@Slf4j
@Component
public class PatientValidationClient {

  private final Channel channel;

  public PatientValidationClient(Channel patientServiceChannel) {
    this.channel = patientServiceChannel;
  }

  public boolean validatePatient(UUID patientId) {
    PatientValidationServiceGrpc.PatientValidationServiceBlockingStub stub =
        PatientValidationServiceGrpc.newBlockingStub(channel);

    PatientValidationRequest request = PatientValidationRequest.newBuilder()
        .setPatientId(patientId.toString())
        .build();

    try {
      PatientValidationResponse response = stub.validatePatient(request);
      log.info("Patient validation response for patientId {}: exists={}", patientId, response.getExists());
      return response.getExists();
    } catch (Exception e) {
      log.error("Patient validation failed for patientId {}: {}", patientId, e.getMessage());
      throw new RuntimeException("Patient validation failed: " + e.getMessage());
    }
  }
}

