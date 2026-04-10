package com.pm.billingservice.controller;

import com.pm.billingservice.dto.BillingAppointmentRequestDTO;
import com.pm.billingservice.dto.BillingAppointmentResponseDTO;
import com.pm.billingservice.service.BillingAppointmentService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billing-appointments")
public class BillingAppointmentController {

  private final BillingAppointmentService billingAppointmentService;

  public BillingAppointmentController(
      BillingAppointmentService billingAppointmentService) {
    this.billingAppointmentService = billingAppointmentService;
  }

  @PostMapping
  public ResponseEntity<BillingAppointmentResponseDTO> createAppointmentReference(
      @Valid @RequestBody BillingAppointmentRequestDTO requestDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(billingAppointmentService.createAppointmentReference(requestDTO));
  }

  @GetMapping
  public ResponseEntity<List<BillingAppointmentResponseDTO>> getAppointmentReferences() {
    return ResponseEntity.ok(
        billingAppointmentService.getAppointmentReferences());
  }
}
