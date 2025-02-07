package com.tectoro.bus_reservation_system.repository;

import com.tectoro.bus_reservation_system.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger,Integer> {
}
