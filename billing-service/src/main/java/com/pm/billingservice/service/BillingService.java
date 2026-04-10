package com.pm.billingservice.service;

import com.pm.billingservice.dto.BillRequestDTO;
import com.pm.billingservice.dto.BillResponseDTO;
import com.pm.billingservice.exception.BillNotFoundException;
import com.pm.billingservice.exception.InvalidBillingReferenceException;
import com.pm.billingservice.mapper.BillingMapper;
import com.pm.billingservice.model.Bill;
import com.pm.billingservice.repository.BillRepository;
import com.pm.billingservice.repository.BillingAppointmentRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class BillingService {

  private final BillRepository billRepository;
  private final BillingAppointmentRepository billingAppointmentRepository;

  public BillingService(BillRepository billRepository,
      BillingAppointmentRepository billingAppointmentRepository) {
    this.billRepository = billRepository;
    this.billingAppointmentRepository = billingAppointmentRepository;
  }

  public List<BillResponseDTO> getBills() {
    return billRepository.findAll().stream().map(BillingMapper::toDTO).toList();
  }

  public BillResponseDTO getBill(UUID id) {
    return BillingMapper.toDTO(findBillById(id));
  }

  public BillResponseDTO createBill(BillRequestDTO requestDTO) {
    validateAppointmentOwnership(requestDTO.getAppointmentId(),
        requestDTO.getPatientId());

    Bill bill = BillingMapper.toModel(requestDTO);
    return BillingMapper.toDTO(billRepository.save(bill));
  }

  public BillResponseDTO updateBill(UUID id, BillRequestDTO requestDTO) {
    validateAppointmentOwnership(requestDTO.getAppointmentId(),
        requestDTO.getPatientId());

    Bill existingBill = findBillById(id);
    BillingMapper.updateModel(existingBill, requestDTO);
    return BillingMapper.toDTO(billRepository.save(existingBill));
  }

  public void deleteBill(UUID id) {
    Bill bill = findBillById(id);
    billRepository.delete(bill);
  }

  private Bill findBillById(UUID id) {
    return billRepository.findById(id).orElseThrow(
        () -> new BillNotFoundException("Bill not found with ID: " + id));
  }

  private void validateAppointmentOwnership(UUID appointmentId, UUID patientId) {
    boolean isValidAppointmentPatientPair =
        billingAppointmentRepository.existsByAppointmentIdAndPatientId(
            appointmentId, patientId);

    if (!isValidAppointmentPatientPair) {
      throw new InvalidBillingReferenceException(
          "No appointment mapping found for appointment ID " + appointmentId
              + " and patient ID " + patientId);
    }
  }
}
