package com.pm.patientservice.grpc;

import com.pm.patientservice.service.PatientService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import patient.validation.PatientValidationRequest;
import patient.validation.PatientValidationResponse;
import patient.validation.PatientValidationServiceGrpc;
import java.util.UUID;

@GrpcService
public class PatientValidationServiceImpl extends PatientValidationServiceGrpc.PatientValidationServiceImplBase {

  @Autowired
  private PatientService patientService;

  @Override
  public void validatePatient(PatientValidationRequest request,
      StreamObserver<PatientValidationResponse> responseObserver) {
    try {
      UUID patientId = UUID.fromString(request.getPatientId());
      var patientDTO = patientService.getPatient(patientId);
      
      PatientValidationResponse response = PatientValidationResponse.newBuilder()
          .setExists(true)
          .setPatientId(patientDTO.getId().toString())
          .setEmail(patientDTO.getEmail())
          .setName(patientDTO.getName())
          .build();
      
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    } catch (Exception e) {
      PatientValidationResponse response = PatientValidationResponse.newBuilder()
          .setExists(false)
          .build();
      
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }
}
