package com.tectoro.bus_reservation_system.model;

import com.tectoro.bus_reservation_system.dto.PassengerDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "transaction_report")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportId;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private  Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Column(nullable = false)
    private LocalDate reportDate;
}
