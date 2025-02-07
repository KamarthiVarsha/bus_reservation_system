package com.tectoro.bus_reservation_system.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private Integer paymentId;
    private PassengerDTO passenger;     // ID of the associated passenger
    private LocalDateTime paymentDate;
}
