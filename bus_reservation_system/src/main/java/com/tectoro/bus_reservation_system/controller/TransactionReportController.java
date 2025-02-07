package com.tectoro.bus_reservation_system.controller;

import com.tectoro.bus_reservation_system.dto.TransactionReportDTO;
import com.tectoro.bus_reservation_system.service.TransactionReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction-reports")
public class TransactionReportController {

    @Autowired
    private TransactionReportService transactionReportService;

    @PostMapping
    public ResponseEntity<TransactionReportDTO> createTransactionReport(@RequestBody TransactionReportDTO transactionReportDTO) {
        return ResponseEntity.ok(transactionReportService.createTransactionReport(transactionReportDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionReportDTO> getTransactionReportById(@PathVariable("id") int reportId) {
        return ResponseEntity.ok(transactionReportService.getTransactionReportById(reportId));
    }

    @GetMapping
    public ResponseEntity<List<TransactionReportDTO>> getAllTransactionReports() {
        return ResponseEntity.ok(transactionReportService.getAllTransactionReports());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionReportDTO> updateTransactionReport(
            @PathVariable("id") int reportId,
            @RequestBody TransactionReportDTO transactionReportDTO) {
        return ResponseEntity.ok(transactionReportService.updateTransactionReport(reportId, transactionReportDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTransactionReport(@PathVariable("id") int reportId) {
        transactionReportService.deleteTransactionReport(reportId);
    }
}
