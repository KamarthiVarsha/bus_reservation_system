package com.tectoro.bus_reservation_system.controller;

import com.tectoro.bus_reservation_system.dto.BusDTO;
import com.tectoro.bus_reservation_system.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping
    public ResponseEntity<BusDTO> createBus(@RequestBody BusDTO busDTO) {
        return ResponseEntity.ok(busService.createBus(busDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusDTO> getBusById(@PathVariable int id) {
        return ResponseEntity.ok(busService.getBusById(id));
    }

    @GetMapping
    public ResponseEntity<List<BusDTO>> getAllBuses() {

        return new ResponseEntity<>(busService.getAllBuses(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusDTO> updateBus(@PathVariable("id") int busId, @RequestBody BusDTO busDTO) {
        return ResponseEntity.ok(busService.updateBus(busId, busDTO));
    }

    @DeleteMapping("/{busId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBus(@PathVariable int busId) {
        busService.deleteBus(busId);

    }
}
