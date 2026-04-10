package com.pm.appointmentservice.controller;

import com.pm.appointmentservice.dto.AppointmentRequestDTO;
import com.pm.appointmentservice.dto.AppointmentResponseDTO;
import com.pm.appointmentservice.dto.UpdateStatusDTO;
import com.pm.appointmentservice.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
@Tag(name = "Appointment", description = "API for managing Appointments")
public class AppointmentController {

  private final AppointmentService appointmentService;

  public AppointmentController(AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
  }

  @GetMapping
  @Operation(summary = "Get Appointments")
  public ResponseEntity<List<AppointmentResponseDTO>> getAppointments() {
    List<AppointmentResponseDTO> appointments = appointmentService.getAppointments();
    return ResponseEntity.ok(appointments);
  }

  @PostMapping
  @Operation(summary = "Create a new Appointment")
  public ResponseEntity<AppointmentResponseDTO> createAppointment(
      @Valid @RequestBody AppointmentRequestDTO requestDTO) {
    AppointmentResponseDTO response = appointmentService.createAppointment(requestDTO);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update Appointment Details")
  public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable UUID id,
      @Valid @RequestBody AppointmentRequestDTO requestDTO) {
    AppointmentResponseDTO response = appointmentService.updateAppointmentDetails(id, requestDTO);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}/status")
  @Operation(summary = "Update Appointment Status")
  public ResponseEntity<AppointmentResponseDTO> updateAppointmentStatus(@PathVariable UUID id,
      @Valid @RequestBody UpdateStatusDTO statusDTO) {
    AppointmentResponseDTO response = appointmentService.updateAppointmentStatus(id, statusDTO);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete an Appointment")
  public ResponseEntity<Void> deleteAppointment(@PathVariable UUID id) {
    appointmentService.deleteAppointment(id);
    return ResponseEntity.noContent().build();
  }
}
