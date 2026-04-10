package com.pm.billingservice.repository;

import com.pm.billingservice.model.BillingAppointment;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingAppointmentRepository extends
    JpaRepository<BillingAppointment, UUID> {

  boolean existsByAppointmentIdAndPatientId(UUID appointmentId, UUID patientId);
}
