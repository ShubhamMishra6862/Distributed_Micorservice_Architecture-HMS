package com.pm.patientservice.search;

import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

public class PatientSearchMapper {
  public static PatientDocument toDocument(Patient patient) {
    PatientDocument document = new PatientDocument();
    document.setId(patient.getId().toString());
    document.setName(patient.getName());
    document.setEmail(patient.getEmail());
    document.setAddress(patient.getAddress());
    document.setHeight(patient.getHeight());
    document.setWeight(patient.getWeight());
    document.setDateOfBirth(patient.getDateOfBirth());
    document.setRegisteredDate(patient.getRegisteredDate());
    return document;
  }

  public static PatientResponseDTO toDTO(PatientDocument document) {
    PatientResponseDTO dto = new PatientResponseDTO();
    dto.setId(document.getId());
    dto.setName(document.getName());
    dto.setEmail(document.getEmail());
    dto.setAddress(document.getAddress());
    dto.setHeight(document.getHeight());
    dto.setWeight(document.getWeight());
    if (document.getDateOfBirth() != null) {
      dto.setDateOfBirth(document.getDateOfBirth());
    }
    return dto;
  }
}

