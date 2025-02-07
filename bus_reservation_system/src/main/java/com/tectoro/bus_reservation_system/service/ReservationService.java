package com.tectoro.bus_reservation_system.service;

import com.tectoro.bus_reservation_system.dto.ReservationDTO;
import com.tectoro.bus_reservation_system.exception.InvalidReservationDateException;
import com.tectoro.bus_reservation_system.exception.ResourceNotFoundException;
import com.tectoro.bus_reservation_system.model.Bus;
import com.tectoro.bus_reservation_system.model.Passenger;
import com.tectoro.bus_reservation_system.model.Reservation;
import com.tectoro.bus_reservation_system.repository.BusRepository;
import com.tectoro.bus_reservation_system.repository.PassengerRepository;
import com.tectoro.bus_reservation_system.repository.ReservationRepository;
import com.tectoro.bus_reservation_system.repository.TransactionReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private TransactionReportRepository transactionReportRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Create Reservation
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();

        // Set Passenger
        Passenger passenger = passengerRepository.findById(reservationDTO.getPassengerId())
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found with id " + reservationDTO.getPassengerId()));
        reservation.setPassenger(passenger);

        // Set Bus
        Bus bus = busRepository.findById(reservationDTO.getBusId())
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id " + reservationDTO.getBusId()));
        reservation.setBus(bus);

        // Set Other Fields
        reservation.setDestination(reservationDTO.getDestination());
        reservation.setDepartureTime(reservationDTO.getDepartureTime());
        reservation.setReservationDate(reservationDTO.getReservationDate());
        Reservation savedReservation = reservationRepository.save(reservation);
        return modelMapper.map(savedReservation, ReservationDTO.class);
    }

    // Get Reservation by ID
    public ReservationDTO getReservationById(int reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + reservationId));
        return modelMapper.map(reservation, ReservationDTO.class);
    }

    // Get All Reservations
    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDTO.class))
                .collect(Collectors.toList());
    }
    // Update Reservation
    public ReservationDTO updateReservation(int reservationId, ReservationDTO reservationDTO) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + reservationId));

        // Update Passenger
        Passenger passenger = passengerRepository.findById(reservationDTO.getPassengerId())
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found with id " + reservationDTO.getPassengerId()));
        reservation.setPassenger(passenger);

        // Update Bus
        Bus bus = busRepository.findById(reservationDTO.getBusId())
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id " + reservationDTO.getBusId()));
        reservation.setBus(bus);

        // Update Other Fields
        reservation.setDestination(reservationDTO.getDestination());
        reservation.setDepartureTime(reservationDTO.getDepartureTime());
        LocalDate reservationDate = reservationDTO.getReservationDate();
        LocalDate currentDate = LocalDate.now();
        LocalDate oneYearLater = currentDate.plusYears(1);

        // Check if the reservation date is in the future or beyond one year
        if (reservationDate.isBefore(currentDate)) {
            throw new InvalidReservationDateException("Reservation date cannot be in the past.");
        }

        if (reservationDate.isAfter(oneYearLater)) {
            throw new InvalidReservationDateException("Reservation date cannot be more than one year from the current date.");
        }
        reservation.setReservationDate(reservationDTO.getReservationDate());

        Reservation updatedReservation = reservationRepository.save(reservation);
        return modelMapper.map(updatedReservation, ReservationDTO.class);
    }
    // Delete Reservation
    public void deleteReservation(int reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + reservationId));
        reservationRepository.delete(reservation);
    }


}
