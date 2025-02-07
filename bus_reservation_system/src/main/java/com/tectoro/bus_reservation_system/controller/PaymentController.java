package com.tectoro.bus_reservation_system.controller;

import com.tectoro.bus_reservation_system.dto.PassengerDTO;
import com.tectoro.bus_reservation_system.dto.PaymentDTO;
import com.tectoro.bus_reservation_system.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Create Payment
    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        PaymentDTO createdPayment = paymentService.createPayment(paymentDTO);
        return ResponseEntity.ok(createdPayment);
    }

    // Get Payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable("id") int paymentId) {
        PaymentDTO paymentDTO = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(paymentDTO);
    }

    // Get All Payments
    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        List<PaymentDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    // Update Payment
    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(
            @PathVariable("id") int paymentId,
            @RequestBody PaymentDTO paymentDTO) {
        PaymentDTO updatedPayment = paymentService.updatePayment(paymentId, paymentDTO);
        return ResponseEntity.ok(updatedPayment);
    }

    // Delete Payment
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePayment(@PathVariable("id") int paymentId) {
        paymentService.deletePayment(paymentId);

    }


}
