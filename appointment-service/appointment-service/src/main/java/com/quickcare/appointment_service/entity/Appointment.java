package com.quickcare.appointment_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private long appointmentId;
    private int appointmentDuration;
    private long doctorId;
    private String doctorName;
    private String doctorEmail;
   private long patientId;
   private String patientName;
   private String patientEmail;
    private LocalDateTime appointmentDateTime;
    private String appointmentStatus;

}
