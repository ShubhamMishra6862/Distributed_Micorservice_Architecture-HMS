package com.pm.billingservice.controller;

import com.pm.billingservice.dto.AppointmentValidationDTO;
import com.pm.billingservice.model.Appointment;
import com.pm.billingservice.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
@Tag(name = "Appointment Validation", description = "API for validating Appointments")
public class AppointmentController {

  private final AppointmentService appointmentService;

  public AppointmentController(AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
  }

  @GetMapping("/validate/{appointmentId}")
  @Operation(summary = "Validate Appointment by ID")
  public ResponseEntity<AppointmentValidationDTO> validateAppointment(@PathVariable UUID appointmentId) {
    boolean exists = appointmentService.validateAppointmentExists(appointmentId);
    
    if (exists) {
      Appointment appointment = appointmentService.getAppointment(appointmentId);
      AppointmentValidationDTO response = new AppointmentValidationDTO(
          appointment.getAppointmentId(),
          appointment.getPatientId(),
          true
      );
      return ResponseEntity.ok(response);
    } else {
      AppointmentValidationDTO response = new AppointmentValidationDTO(appointmentId, null, false);
      return ResponseEntity.ok(response);
    }
  }

  @GetMapping("/{appointmentId}")
  @Operation(summary = "Get Appointment Details by ID")
  public ResponseEntity<AppointmentValidationDTO> getAppointment(@PathVariable UUID appointmentId) {
    Appointment appointment = appointmentService.getAppointment(appointmentId);
    AppointmentValidationDTO response = new AppointmentValidationDTO(
        appointment.getAppointmentId(),
        appointment.getPatientId(),
        true
    );
    return ResponseEntity.ok(response);
  }
}
