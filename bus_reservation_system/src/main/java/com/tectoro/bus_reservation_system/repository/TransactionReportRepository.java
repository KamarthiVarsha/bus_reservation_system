package com.tectoro.bus_reservation_system.repository;

import com.tectoro.bus_reservation_system.model.TransactionReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionReportRepository extends JpaRepository<TransactionReport,Integer> {
}
