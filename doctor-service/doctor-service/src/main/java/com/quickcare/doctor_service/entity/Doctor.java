package com.quickcare.doctor_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long doctorId;
    private String doctorName;
    private String specialization;
    private Boolean availability=false;
    private LocalTime startTime;
    private LocalTime endTime;
}

