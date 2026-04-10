package com.pm.billingservice.service;

import com.pm.billingservice.dto.BillingAppointmentRequestDTO;
import com.pm.billingservice.dto.BillingAppointmentResponseDTO;
import com.pm.billingservice.exception.AppointmentReferenceAlreadyExistsException;
import com.pm.billingservice.mapper.BillingMapper;
import com.pm.billingservice.model.BillingAppointment;
import com.pm.billingservice.repository.BillingAppointmentRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BillingAppointmentService {

  private final BillingAppointmentRepository billingAppointmentRepository;

  public BillingAppointmentService(
      BillingAppointmentRepository billingAppointmentRepository) {
    this.billingAppointmentRepository = billingAppointmentRepository;
  }

  public BillingAppointmentResponseDTO createAppointmentReference(
      BillingAppointmentRequestDTO requestDTO) {
    if (billingAppointmentRepository.existsById(requestDTO.getAppointmentId())) {
      throw new AppointmentReferenceAlreadyExistsException(
          "Appointment reference already exists for appointment ID: "
              + requestDTO.getAppointmentId());
    }

    BillingAppointment billingAppointment = new BillingAppointment();
    billingAppointment.setAppointmentId(requestDTO.getAppointmentId());
    billingAppointment.setPatientId(requestDTO.getPatientId());

    return BillingMapper.toDTO(
        billingAppointmentRepository.save(billingAppointment));
  }

  public List<BillingAppointmentResponseDTO> getAppointmentReferences() {
    return billingAppointmentRepository.findAll().stream()
        .map(BillingMapper::toDTO)
        .toList();
  }
}
