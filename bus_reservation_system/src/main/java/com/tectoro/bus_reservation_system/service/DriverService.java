package com.tectoro.bus_reservation_system.service;
import com.tectoro.bus_reservation_system.dto.BusDTO;
import com.tectoro.bus_reservation_system.dto.BusDTOMapper;
import com.tectoro.bus_reservation_system.dto.DriverDTO;
import com.tectoro.bus_reservation_system.exception.ResourceNotFoundException;
import com.tectoro.bus_reservation_system.model.Bus;
import com.tectoro.bus_reservation_system.model.Driver;
import com.tectoro.bus_reservation_system.repository.BusRepository;
import com.tectoro.bus_reservation_system.repository.DriverRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Save a new driver
    public DriverDTO saveDriver(DriverDTO driverDTO) {
        Driver driver = modelMapper.map(driverDTO, Driver.class);
        Driver savedDriver = driverRepository.save(driver);
        return modelMapper.map(savedDriver, DriverDTO.class);
    }

    // Get a driver by ID
    public DriverDTO getDriverById(Integer driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with ID: " + driverId));

        DriverDTO driverDTO = modelMapper.map(driver, DriverDTO.class);

        // Map the buses to BusDTO without driverId
        List<BusDTO> busDTOs = driver.getBuses().stream()
                .map(BusDTOMapper::mapToBusDTOWithoutDriverId)
                .collect(Collectors.toList());

        driverDTO.setBuses(busDTOs);

        return driverDTO;
    }


    // Get all drivers
    public List<DriverDTO> getAllDrivers() {
        List<Driver> drivers = driverRepository.findAll();

        return drivers.stream().map(driver -> {
            DriverDTO driverDTO = modelMapper.map(driver, DriverDTO.class);

            List<BusDTO> busDTOs = driver.getBuses().stream()
                    .map(BusDTOMapper::mapToBusDTOWithoutDriverId)
                    .collect(Collectors.toList());

            driverDTO.setBuses(busDTOs);

            return driverDTO;
        }).collect(Collectors.toList());
    }



    // Update a driver
    public DriverDTO updateDriver(Integer driverId, DriverDTO driverDTO) {
        // Find the existing driver or throw an exception if not found
        Driver existingDriver = driverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with ID: " + driverId));

        // Update driver name
        existingDriver.setName(driverDTO.getName());

        // Update buses assigned to the driver
        List<Bus> buses = driverDTO.getBuses().stream()
                .map(busDTO -> {
                    Bus bus = busRepository.findById(busDTO.getBusId())
                            .orElseThrow(() -> new ResourceNotFoundException("Bus not found with ID: " + busDTO.getBusId()));

                    // Optionally update bus details if necessary
                    bus.setBusNumber(busDTO.getBusNumber());
                    bus.setBusStatus(busDTO.getBusStatus());
                    bus.setBusSeats(busDTO.getBusSeats());

                    // Associate the bus with the driver
                    bus.setDriver(existingDriver);
                    return bus;
                })
                .collect(Collectors.toList());

        existingDriver.setBuses(buses);

        // Save the updated driver
        Driver updatedDriver = driverRepository.save(existingDriver);

        // Map the updated driver to DriverDTO
        DriverDTO updatedDriverDTO = modelMapper.map(updatedDriver, DriverDTO.class);

        // Convert buses to BusDTO without driverId for the response
        List<BusDTO> busDTOs = updatedDriver.getBuses().stream()
                .map(BusDTOMapper::mapToBusDTOWithoutDriverId)
                .collect(Collectors.toList());

        updatedDriverDTO.setBuses(busDTOs);

        return updatedDriverDTO;
    }


    // Delete a driver
    public void deleteDriver(Integer driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with ID: " + driverId));
        driverRepository.delete(driver);
    }

}

