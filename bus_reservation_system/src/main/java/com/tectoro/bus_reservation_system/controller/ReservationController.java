package com.tectoro.bus_reservation_system.controller;

import com.tectoro.bus_reservation_system.dto.ReservationDTO;
import com.tectoro.bus_reservation_system.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        return ResponseEntity.ok(reservationService.createReservation(reservationDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable("id") int reservationId) {
        return ResponseEntity.ok(reservationService.getReservationById(reservationId));
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable("id") int reservationId, @RequestBody ReservationDTO reservationDTO) {
        return ResponseEntity.ok(reservationService.updateReservation(reservationId, reservationDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReservation(@PathVariable("id") int reservationId) {
        reservationService.deleteReservation(reservationId);
    }
}
