package com.tectoro.bus_reservation_system.repository;

import com.tectoro.bus_reservation_system.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus,Integer> {
    Bus findByBusNumber(Integer busNumber);
}
