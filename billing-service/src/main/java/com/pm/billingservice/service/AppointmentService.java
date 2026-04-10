package com.pm.billingservice.service;

import com.pm.billingservice.model.Appointment;
import com.pm.billingservice.exception.AppointmentNotFoundException;
import com.pm.billingservice.repository.AppointmentRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

  private final AppointmentRepository appointmentRepository;

  public AppointmentService(AppointmentRepository appointmentRepository) {
    this.appointmentRepository = appointmentRepository;
  }

  public boolean validateAppointmentExists(UUID appointmentId) {
    return appointmentRepository.existsByAppointmentId(appointmentId);
  }

  public Appointment getAppointment(UUID appointmentId) {
    return appointmentRepository.findByAppointmentId(appointmentId)
        .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with ID: " + appointmentId));
  }

  public Appointment getAppointmentById(UUID id) {
    return appointmentRepository.findById(id)
        .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with ID: " + id));
  }
}
