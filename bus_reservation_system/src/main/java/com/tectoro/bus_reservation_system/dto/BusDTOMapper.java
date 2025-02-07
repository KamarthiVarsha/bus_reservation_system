package com.tectoro.bus_reservation_system.dto;

import com.tectoro.bus_reservation_system.model.Bus;

public class BusDTOMapper {

    // Map Bus to BusDTO including driverId
    public static BusDTO mapToBusDTO(Bus bus) {
        return new BusDTO(
                bus.getBusId(),
                bus.getBusNumber(),
                bus.getBusStatus(),
                bus.getBusSeats(),
                bus.getDriver() != null ? bus.getDriver().getDriverId() : null
        );
    }

    // Map Bus to BusDTO excluding driverId (for nested driver response)
    public static BusDTO mapToBusDTOWithoutDriverId(Bus bus) {
        return new BusDTO(
                bus.getBusId(),
                bus.getBusNumber(),
                bus.getBusStatus(),
                bus.getBusSeats()
        );
    }
}
