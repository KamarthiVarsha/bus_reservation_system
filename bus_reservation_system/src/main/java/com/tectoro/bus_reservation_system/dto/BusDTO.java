package com.tectoro.bus_reservation_system.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusDTO {
    private Integer busId;
    private Integer busNumber;
    private String busStatus;
    private Integer busSeats;
    private Integer driverId;

    public BusDTO(Integer busId, Integer busNumber, String busStatus, Integer busSeats) {
        this.busId = busId;
        this.busNumber = busNumber;
        this.busStatus = busStatus;
        this.busSeats = busSeats;
    }
}
