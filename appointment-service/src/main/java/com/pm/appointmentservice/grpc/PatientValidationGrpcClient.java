package com.pm.appointmentservice.grpc;

import io.grpc.Channel;
import net.devh.boot.grpc.client.channelfactory.GrpcChannelConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import patient.validation.PatientValidationRequest;
import patient.validation.PatientValidationResponse;
import patient.validation.PatientValidationServiceGrpc;
import java.util.UUID;

@Configuration
public class PatientValidationGrpcClient {

  private final Channel channel;

  public PatientValidationGrpcClient(Channel patientServiceChannel) {
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
      return response.getExists();
    } catch (Exception e) {
      throw new RuntimeException("Patient validation failed: " + e.getMessage());
    }
  }

  @Bean
  public Channel patientServiceChannel() {
    return io.grpc.ManagedChannelBuilder.forAddress("patient-service", 9000)
        .usePlaintext()
        .build();
  }
}
