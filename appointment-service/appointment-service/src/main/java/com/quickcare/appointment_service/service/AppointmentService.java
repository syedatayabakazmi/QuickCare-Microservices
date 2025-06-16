package com.quickcare.appointment_service.service;

import com.quickcare.appointment_service.entity.Appointment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {
    Appointment bookAppointment(Appointment appointment);
    void cancelAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    void updateAppointment(Appointment appointment);
    public Appointment findAppointmentById(int id);
    public List<Appointment> getAppointmentByPatientId(int patientId);
    public List<Appointment> getAppointmentByDoctorId(int doctorId);
    List<Appointment> getAppointmentByDate(LocalDateTime date);

    void cancelAppointmentByDoctor(int appointmentId);
}
