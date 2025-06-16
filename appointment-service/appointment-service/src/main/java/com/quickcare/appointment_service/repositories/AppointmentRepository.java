package com.quickcare.appointment_service.repositories;

import com.quickcare.appointment_service.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findAppointmentByAppointmentId(long appointmentId);

    List<Appointment> getAppointmentsByDoctorId(long doctorId);

    List<Appointment> getAppointmentsByPatientId(long patientId);

    List<Appointment> getAppointmentsByAppointmentDateTime(LocalDateTime appointmentDateTime);

    boolean existsByDoctorIdAndAppointmentDateTime(long doctorId, LocalDateTime appointmentDateTime);
    boolean existsByPatientIdAndAppointmentDateTime(long patientId, LocalDateTime appointmentDateTime);

    void deleteAppointmentByDoctorId(long doctorId);
}
