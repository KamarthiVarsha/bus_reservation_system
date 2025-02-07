package com.tectoro.bus_reservation_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Table(name = "passenger")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer passengerId;

    @Column(nullable = false, length = 255)
    private String fname;

    @Column(nullable = false, length = 255)
    private String lname;

    @Column(nullable = false)
    private Integer gender;

    @Column(nullable = false)
    private Integer age;

    @Column(name = "contact_add", nullable = false)
    private String contactAddress;

    // One passenger can have multiple reservations
    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    // One passenger can have multiple payments
    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;
}

