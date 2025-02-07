package com.tectoro.bus_reservation_system.repository;

import com.tectoro.bus_reservation_system.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
