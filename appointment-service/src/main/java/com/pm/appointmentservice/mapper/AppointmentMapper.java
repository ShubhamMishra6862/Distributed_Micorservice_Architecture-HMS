package com.pm.appointmentservice.mapper;

import com.pm.appointmentservice.dto.AppointmentRequestDTO;
import com.pm.appointmentservice.dto.AppointmentResponseDTO;
import com.pm.appointmentservice.model.Appointment;
import com.pm.appointmentservice.model.AppointmentStatus;

public class AppointmentMapper {

  public static Appointment toModel(AppointmentRequestDTO dto) {
    return new Appointment(dto.getPatientId(), dto.getDetails(), AppointmentStatus.SCHEDULED);
  }

  public static AppointmentResponseDTO toDTO(Appointment appointment) {
    AppointmentResponseDTO dto = new AppointmentResponseDTO();
    dto.setId(appointment.getId());
    dto.setPatientId(appointment.getPatientId());
    dto.setDetails(appointment.getDetails());
    dto.setStatus(appointment.getStatus());
    dto.setCreatedAt(appointment.getCreatedAt());
    dto.setUpdatedAt(appointment.getUpdatedAt());
    return dto;
  }
}
