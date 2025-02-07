package com.tectoro.bus_reservation_system.service;

import com.tectoro.bus_reservation_system.dto.TransactionReportDTO;
import com.tectoro.bus_reservation_system.exception.ResourceNotFoundException;
import com.tectoro.bus_reservation_system.model.TransactionReport;
import com.tectoro.bus_reservation_system.model.Passenger;
import com.tectoro.bus_reservation_system.model.Reservation;
import com.tectoro.bus_reservation_system.model.Payment;
import com.tectoro.bus_reservation_system.repository.PassengerRepository;
import com.tectoro.bus_reservation_system.repository.PaymentRepository;
import com.tectoro.bus_reservation_system.repository.ReservationRepository;
import com.tectoro.bus_reservation_system.repository.TransactionReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionReportService {

    @Autowired
    private TransactionReportRepository transactionReportRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public TransactionReportDTO createTransactionReport(TransactionReportDTO transactionReportDTO) {
        TransactionReport transactionReport = new TransactionReport();

        // Set Passenger
        Passenger passenger = passengerRepository.findById(transactionReportDTO.getPassengerId())
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found with id " + transactionReportDTO.getPassengerId()));
        transactionReport.setPassenger(passenger);

        // Set Reservation
        Reservation reservation = reservationRepository.findById(transactionReportDTO.getReservationId())
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + transactionReportDTO.getReservationId()));
        transactionReport.setReservation(reservation);

        // Set Payment
        Payment payment = paymentRepository.findById(transactionReportDTO.getPaymentId())
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id " + transactionReportDTO.getPaymentId()));
        transactionReport.setPayment(payment);

        // Set Report Date
        transactionReport.setReportDate(transactionReportDTO.getReportDate());

        TransactionReport savedReport = transactionReportRepository.save(transactionReport);
        return modelMapper.map(savedReport, TransactionReportDTO.class);
    }

    public TransactionReportDTO getTransactionReportById(int reportId) {
        TransactionReport transactionReport = transactionReportRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction Report not found with id " + reportId));
        return modelMapper.map(transactionReport, TransactionReportDTO.class);
    }

    public List<TransactionReportDTO> getAllTransactionReports() {
        return transactionReportRepository.findAll().stream()
                .map(report -> modelMapper.map(report, TransactionReportDTO.class))
                .toList();
    }

    public TransactionReportDTO updateTransactionReport(int reportId, TransactionReportDTO transactionReportDTO) {
        TransactionReport transactionReport = transactionReportRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction Report not found with id " + reportId));

        // Update Passenger
        Passenger passenger = passengerRepository.findById(transactionReportDTO.getPassengerId())
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found with id " + transactionReportDTO.getPassengerId()));
        transactionReport.setPassenger(passenger);

        // Update Reservation
        Reservation reservation = reservationRepository.findById(transactionReportDTO.getReservationId())
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + transactionReportDTO.getReservationId()));
        transactionReport.setReservation(reservation);

        // Update Payment
        Payment payment = paymentRepository.findById(transactionReportDTO.getPaymentId())
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id " + transactionReportDTO.getPaymentId()));
        transactionReport.setPayment(payment);

        // Update Report Date
        transactionReport.setReportDate(transactionReportDTO.getReportDate());

        TransactionReport updatedReport = transactionReportRepository.save(transactionReport);
        return modelMapper.map(updatedReport, TransactionReportDTO.class);
    }

    public void deleteTransactionReport(int reportId) {
        TransactionReport transactionReport = transactionReportRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction Report not found with id " + reportId));
        transactionReportRepository.delete(transactionReport);
    }
}
