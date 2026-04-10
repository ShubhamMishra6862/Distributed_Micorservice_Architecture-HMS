package com.pm.billingservice.kafka;

import com.pm.billingservice.model.Appointment;
import com.pm.billingservice.repository.AppointmentRepository;
import java.util.UUID;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

  private final AppointmentRepository appointmentRepository;

  public KafkaConsumer(AppointmentRepository appointmentRepository) {
    this.appointmentRepository = appointmentRepository;
  }

  @KafkaListener(topics = "appointment-events", groupId = "billing-group")
  public void consumeAppointmentEvent(String message) {
    String[] parts = message.split(",");
    if (parts.length == 3) {
      UUID appointmentId = UUID.fromString(parts[0]);
      UUID patientId = UUID.fromString(parts[1]);
      String eventType = parts[2];
      Appointment appointment = new Appointment(appointmentId, patientId, eventType);
      appointmentRepository.save(appointment);
    }
  }
}
