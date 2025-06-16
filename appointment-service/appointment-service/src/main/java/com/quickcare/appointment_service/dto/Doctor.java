package com.quickcare.appointment_service.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class Doctor {
    long doctorId;
    String doctorName;
    String specialization;
    Boolean availability=false;
    private LocalTime startTime;
    private LocalTime endTime;
}
