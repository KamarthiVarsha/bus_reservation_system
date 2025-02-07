package com.tectoro.bus_reservation_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private Integer reservationId;
    private Integer passengerId;
    private Integer busId;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDate reservationDate;

    public ReservationDTO(Integer passengerId, Integer busId, String destination, LocalDateTime departureTime, LocalDate reservationDate) {
        this.passengerId = passengerId;
        this.busId = busId;
        this.destination = destination;
        this.departureTime = departureTime;
        this.reservationDate = reservationDate;
    }
}
