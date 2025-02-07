package com.tectoro.bus_reservation_system.dto;
import lombok.Data;

import java.util.List;

@Data
public class PassengerDTO {
    private Integer passengerId;
    private String fname;
    private String lname;
    private Integer gender;
    private Integer age;
    private String contactAddress;

}

