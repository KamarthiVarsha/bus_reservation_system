package com.tectoro.bus_reservation_system.service;

import com.tectoro.bus_reservation_system.dto.PaymentDTO;
import com.tectoro.bus_reservation_system.exception.InvalidPaymentDateException;
import com.tectoro.bus_reservation_system.exception.ResourceNotFoundException;
import com.tectoro.bus_reservation_system.model.Passenger;
import com.tectoro.bus_reservation_system.model.Payment;
import com.tectoro.bus_reservation_system.repository.PassengerRepository;
import com.tectoro.bus_reservation_system.repository.PaymentRepository;
import com.tectoro.bus_reservation_system.repository.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Create Payment
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payment payment = new Payment();

        // Set Passenger
        Passenger passenger = passengerRepository.findById(paymentDTO.getPassenger().getPassengerId())
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found with id " + paymentDTO.getPassenger().getPassengerId()));
        payment.setPassenger(passenger);


        // Set Payment Date
        payment.setPaymentDate(paymentDTO.getPaymentDate());

        Payment savedPayment = paymentRepository.save(payment);
        return modelMapper.map(savedPayment, PaymentDTO.class);
    }

    // Get Payment by ID
    public PaymentDTO getPaymentById(int paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id " + paymentId));
        return modelMapper.map(payment, PaymentDTO.class);
    }

    // Get All Payments
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(payment -> {
                    PaymentDTO paymentDTO = modelMapper.map(payment, PaymentDTO.class);
                    paymentDTO.getPassenger().setPassengerId(payment.getPassenger().getPassengerId());
                    return paymentDTO;
                })
                .collect(Collectors.toList());
    }

    // Update Payment
    public PaymentDTO updatePayment(int paymentId, PaymentDTO paymentDTO) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id " + paymentId));

        // Update Passenger
        Passenger passenger = passengerRepository.findById(paymentDTO.getPassenger().getPassengerId())
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found with id " + paymentDTO.getPassenger().getPassengerId()));
        payment.setPassenger(passenger);

        // Update Payment Date

        if (paymentDTO.getPaymentDate().isBefore(LocalDateTime.now())) {
            throw new InvalidPaymentDateException("Payment date cannot be set to a past date.");
        }
        payment.setPaymentDate(paymentDTO.getPaymentDate());

        Payment updatedPayment = paymentRepository.save(payment);
        return modelMapper.map(updatedPayment, PaymentDTO.class);
    }

    // Delete Payment
    public void deletePayment(int paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id " + paymentId));
        paymentRepository.delete(payment);
    }



}
