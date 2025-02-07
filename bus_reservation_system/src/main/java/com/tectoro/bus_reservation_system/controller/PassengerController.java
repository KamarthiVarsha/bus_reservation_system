package com.tectoro.bus_reservation_system.controller;
import com.tectoro.bus_reservation_system.dto.PassengerDTO;
import com.tectoro.bus_reservation_system.service.PassengerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    // Create a new passenger
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PassengerDTO createPassenger(@RequestBody PassengerDTO passengerDTO) {
        return passengerService.savePassenger(passengerDTO);
    }

    // Get a passenger by ID
    @GetMapping("/{id}")
    public PassengerDTO getPassengerById(@PathVariable Integer id) {
        return passengerService.getPassengerById(id);
    }

    // Get all passengers
    @GetMapping
    public List<PassengerDTO> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    // Update a passenger
    @PutMapping("/{id}")
    public PassengerDTO updatePassenger(@PathVariable Integer id, @RequestBody PassengerDTO passengerDTO) {
        return passengerService.updatePassenger(id, passengerDTO);
    }

    // Delete a passenger
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePassenger(@PathVariable Integer id) {
        passengerService.deletePassenger(id);
    }
}

