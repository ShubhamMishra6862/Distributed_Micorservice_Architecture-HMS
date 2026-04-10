package com.pm.billingservice.repository;

import com.pm.billingservice.model.Bill;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, UUID> {
}
