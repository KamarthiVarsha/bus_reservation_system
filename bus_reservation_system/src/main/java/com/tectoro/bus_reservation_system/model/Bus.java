package com.tectoro.bus_reservation_system.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Table(name = "bus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer busId;

    @Column(nullable = false,unique = true)
    private Integer busNumber;

    @Column(nullable = false, length = 255)
    private String busStatus;

    @Column(nullable = false)
    private Integer busSeats;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "driverId")
    private Driver driver;

    // One bus can have multiple reservations
    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;


    public Bus(Integer busId, Integer busNumber, String busStatus, Integer busSeats) {
        this.busId = busId;
        this.busNumber = busNumber;
        this.busStatus = busStatus;
        this.busSeats = busSeats;
    }

    public Bus(Integer busId, Integer busNumber, String busStatus, Integer busSeats, Driver driver) {
        this.busId = busId;
        this.busNumber = busNumber;
        this.busStatus = busStatus;
        this.busSeats = busSeats;
        this.driver = driver;
    }
}

