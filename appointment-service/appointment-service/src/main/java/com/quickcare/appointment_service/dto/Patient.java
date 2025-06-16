package com.quickcare.appointment_service.dto;

import lombok.Data;

@Data
public class Patient {
    long id;
    String name;
    String email;
    String phone;
}
