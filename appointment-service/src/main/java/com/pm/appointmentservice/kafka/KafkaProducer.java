package com.pm.appointmentservice.kafka;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaProducer {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendAppointmentEvent(UUID appointmentId, UUID patientId, String eventType) {
    try {
      String message = appointmentId.toString() + "," + patientId.toString() + "," + eventType;
      kafkaTemplate.send("appointment-events", message);
      log.info("KAFKA: Payload sent to Kafka Topic: {}", message);
    }
    catch(Exception e) {
      log.error("KAFKA: Unable to send message to the Kafka Topic. {}",e.getMessage());
    }
  }
}
