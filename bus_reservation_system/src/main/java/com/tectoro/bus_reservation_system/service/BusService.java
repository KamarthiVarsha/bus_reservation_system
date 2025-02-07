package com.tectoro.bus_reservation_system.service;

import com.tectoro.bus_reservation_system.dto.BusDTO;
import com.tectoro.bus_reservation_system.dto.BusDTOMapper;
import com.tectoro.bus_reservation_system.exception.BusAlreadyExistsException;
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
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DriverRepository driverRepository;


    public BusDTO getBusById(int busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id " + busId));

        BusDTO busDTO = modelMapper.map(bus, BusDTO.class);
        busDTO.setDriverId(bus.getDriver() != null ? bus.getDriver().getDriverId() : null); // Set only driverId
        return busDTO;
    }


    public List<BusDTO> getAllBuses() {
        List<Bus> buses = busRepository.findAll();

        return buses.stream()
                .map(BusDTOMapper::mapToBusDTO)  // Include driverId
                .collect(Collectors.toList());
    }

    public void deleteBus(int busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id " + busId));
        busRepository.delete(bus);
    }
    public BusDTO createBus(BusDTO busDTO) {

        Bus existingBus = busRepository.findByBusNumber(busDTO.getBusNumber());
        if (existingBus != null) {
            throw new BusAlreadyExistsException("Bus with number " + busDTO.getBusNumber() + " already exists.");
        }

        Bus bus = modelMapper.map(busDTO, Bus.class);

        // Fetch and set the driver
        if (busDTO.getDriverId() != null) {
            Driver driver = driverRepository.findById(busDTO.getDriverId())
                    .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id " + busDTO.getDriverId()));
            bus.setDriver(driver);
        }

        Bus savedBus = busRepository.save(bus);
        BusDTO responseDTO = modelMapper.map(savedBus, BusDTO.class);
        responseDTO.setDriverId(savedBus.getDriver().getDriverId()); // Set only the driverId
        return responseDTO;
    }

    public BusDTO updateBus(int busId, BusDTO busDTO) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id " + busId));

        // Fetch and set the driver
        if (busDTO.getDriverId() != null) {
            Driver driver = driverRepository.findById(busDTO.getDriverId())
                    .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id " + busDTO.getDriverId()));
            bus.setDriver(driver);
        }
        //handling busnumber feild
        if (!bus.getBusNumber().equals(busDTO.getBusNumber())) {
            throw new IllegalArgumentException("Changing the bus number is not allowed.");
        }

        // Update other fields

        bus.setBusStatus(busDTO.getBusStatus());
        bus.setBusSeats(busDTO.getBusSeats());

        Bus updatedBus = busRepository.save(bus);
        BusDTO responseDTO = modelMapper.map(updatedBus, BusDTO.class);
        responseDTO.setDriverId(updatedBus.getDriver().getDriverId()); // Set only the driverId
        return responseDTO;
    }

}

