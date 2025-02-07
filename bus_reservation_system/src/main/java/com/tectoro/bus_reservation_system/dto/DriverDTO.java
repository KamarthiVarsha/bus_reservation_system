package com.tectoro.bus_reservation_system.dto;
import com.tectoro.bus_reservation_system.model.Bus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {
    private Integer driverId;
    private String name;
    private List<BusDTO> buses;
    // List of IDs of buses driven by this driver

    public DriverDTO(Integer driverId, String name) {
        this.driverId = driverId;
        this.name = name;
    }


}
