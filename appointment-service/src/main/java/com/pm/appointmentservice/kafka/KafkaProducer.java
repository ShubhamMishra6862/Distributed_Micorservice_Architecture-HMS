package com.pm.appointmentservice.kafka;

import java.util.UUID;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendAppointmentEvent(UUID appointmentId, UUID patientId, String eventType) {
    String message = appointmentId.toString() + "," + patientId.toString() + "," + eventType;
    kafkaTemplate.send("appointment-events", message);
  }
}
