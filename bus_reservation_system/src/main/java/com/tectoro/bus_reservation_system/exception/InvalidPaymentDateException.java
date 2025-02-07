package com.tectoro.bus_reservation_system.exception;

public class InvalidPaymentDateException extends RuntimeException {
    public InvalidPaymentDateException(String message) {
        super(message);
    }
}
