package com.tectoro.bus_reservation_system.exception;

public class BusAlreadyExistsException extends RuntimeException {
    public BusAlreadyExistsException(String message) {
        super(message);
    }
}
