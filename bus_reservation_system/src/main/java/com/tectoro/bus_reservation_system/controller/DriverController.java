package com.tectoro.bus_reservation_system.controller;
import com.tectoro.bus_reservation_system.dto.DriverDTO;
import com.tectoro.bus_reservation_system.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {

        this.driverService = driverService;
    }

    // Create a new driver
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@RequestBody DriverDTO driverDTO) {
        return driverService.saveDriver(driverDTO);
    }

    // Get a driver by ID
    @GetMapping("/{id}")
    public DriverDTO getDriverById(@PathVariable Integer id) {
        return driverService.getDriverById(id);
    }

    // Get all drivers
    @GetMapping
    public List<DriverDTO> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    // Update a driver
    @PutMapping("/{id}")
    public DriverDTO updateDriver(@PathVariable Integer id, @RequestBody DriverDTO driverDTO) {
        return driverService.updateDriver(id, driverDTO);
    }

    // Delete a driver
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDriver(@PathVariable Integer id) {
        driverService.deleteDriver(id);
    }
}

