package com.tectoro.bus_reservation_system.repository;

import com.tectoro.bus_reservation_system.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Integer> {
}
