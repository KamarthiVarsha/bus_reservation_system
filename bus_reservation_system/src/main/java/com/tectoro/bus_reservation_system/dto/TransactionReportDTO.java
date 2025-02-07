package com.tectoro.bus_reservation_system.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TransactionReportDTO {
    private Integer reportId;
    private Integer passengerId;    // ID of the associated passenger
    private Integer reservationId; // ID of the associated reservation
    private Integer paymentId;     // ID of the associated payment
    private LocalDate reportDate;
}
