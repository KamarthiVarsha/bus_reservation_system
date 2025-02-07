package com.tectoro.bus_reservation_system.service;
import com.tectoro.bus_reservation_system.dto.PassengerDTO;
import com.tectoro.bus_reservation_system.exception.ResourceNotFoundException;
import com.tectoro.bus_reservation_system.model.Passenger;
import com.tectoro.bus_reservation_system.repository.PassengerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Save a new passenger
    public PassengerDTO savePassenger(PassengerDTO passengerDTO) {
        Passenger passenger = modelMapper.map(passengerDTO, Passenger.class);
        Passenger savedPassenger = passengerRepository.save(passenger);
        return modelMapper.map(savedPassenger, PassengerDTO.class);
    }

    // Get a passenger by ID
    public PassengerDTO getPassengerById(Integer passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found with ID: " + passengerId));
        return modelMapper.map(passenger, PassengerDTO.class);
    }

    // Get all passengers
    public List<PassengerDTO> getAllPassengers() {
        List<Passenger> passengers = passengerRepository.findAll();
        return passengers.stream()
                .map(passenger -> modelMapper.map(passenger, PassengerDTO.class))
                .collect(Collectors.toList());
    }

    // Update a passenger
    public PassengerDTO updatePassenger(Integer passengerId, PassengerDTO passengerDTO) {
        Passenger existingPassenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found with ID: " + passengerId));

        existingPassenger.setFname(passengerDTO.getFname());
        existingPassenger.setLname(passengerDTO.getLname());
        existingPassenger.setGender(passengerDTO.getGender());
        existingPassenger.setAge(passengerDTO.getAge());
        existingPassenger.setContactAddress(passengerDTO.getContactAddress());

        Passenger updatedPassenger = passengerRepository.save(existingPassenger);
        return modelMapper.map(updatedPassenger, PassengerDTO.class);
    }

    // Delete a passenger
    public void deletePassenger(Integer passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found with ID: " + passengerId));
        passengerRepository.delete(passenger);
    }
}

