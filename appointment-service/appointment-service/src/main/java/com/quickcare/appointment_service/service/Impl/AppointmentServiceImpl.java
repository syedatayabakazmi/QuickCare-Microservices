package com.quickcare.appointment_service.service.Impl;

import com.quickcare.appointment_service.clients.DoctorClient;
import com.quickcare.appointment_service.clients.NotificationClient;
import com.quickcare.appointment_service.clients.PatientClient;
import com.quickcare.appointment_service.dto.Doctor;
import com.quickcare.appointment_service.entity.Appointment;
import com.quickcare.appointment_service.repositories.AppointmentRepository;
import com.quickcare.appointment_service.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    NotificationClient notificationClient;
    @Autowired
    private DoctorClient doctorClient;


    @Override
    public Appointment bookAppointment(Appointment appointment) {
        LocalDateTime newStart = appointment.getAppointmentDateTime();
        LocalDateTime newEnd = newStart.plusMinutes(appointment.getAppointmentDuration());

        // Doctor conflict check
        List<Appointment> doctorAppointments = appointmentRepository.getAppointmentsByDoctorId(appointment.getDoctorId());
        for (Appointment existing : doctorAppointments) {
            LocalDateTime existingStart = existing.getAppointmentDateTime();
            LocalDateTime existingEnd = existingStart.plusMinutes(existing.getAppointmentDuration());
            if (newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart)) {
                throw new RuntimeException("Doctor already has an overlapping appointment.");
            }
        }

        // Patient conflict check
        List<Appointment> patientAppointments = appointmentRepository.getAppointmentsByPatientId(appointment.getPatientId());
        for (Appointment existing : patientAppointments) {
            LocalDateTime existingStart = existing.getAppointmentDateTime();
            LocalDateTime existingEnd = existingStart.plusMinutes(existing.getAppointmentDuration());
            if (newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart)) {
                throw new RuntimeException("Patient already has an overlapping appointment.");
            }
        }

        Doctor doctor = doctorClient.getDoctorById((int) appointment.getDoctorId());

        LocalDateTime startdatetime = appointment.getAppointmentDateTime();// LocalTime field in Appointment
       LocalTime startTime = startdatetime.toLocalTime();
        LocalTime endTime = startTime.plusMinutes(appointment.getAppointmentDuration());

        if (startTime.isBefore(doctor.getStartTime()) ||
                endTime.isAfter(doctor.getEndTime())) {
            throw new IllegalArgumentException("Appointment is outside doctor's working hours");
        }

        appointment.setAppointmentStatus("SCHEDULED");

        // Notification parameters
        String patientEmail = appointment.getPatientEmail();
        String patientName = appointment.getPatientName();
        String doctorEmail = appointment.getDoctorEmail();
        String doctorName = appointment.getDoctorName();
        String appointmentDateTime = appointment.getAppointmentDateTime().toString();

        // Send one call to notification service with full data
        notificationClient.notifyAppointment(patientEmail, patientName, doctorEmail, doctorName, appointmentDateTime);

        return appointmentRepository.save(appointment);
    }


    @Override
    public void cancelAppointment(Appointment appointment) {

        appointmentRepository.delete(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public void updateAppointment(Appointment appointment) {
    appointmentRepository.save(appointment);
    }

    @Override
    public Appointment findAppointmentById(int id) {

        return appointmentRepository.findAppointmentByAppointmentId((id));
    }


    @Override
    public List<Appointment> getAppointmentByPatientId(int patientId) {

        return appointmentRepository.getAppointmentsByPatientId(patientId);
    }

    @Override
    public List<Appointment> getAppointmentByDoctorId(int doctorId) {
        return appointmentRepository.getAppointmentsByDoctorId((doctorId));
    }

    @Override
    public List<Appointment> getAppointmentByDate(LocalDateTime date) {
        return appointmentRepository.getAppointmentsByAppointmentDateTime(date);
    }

    @Override
    public void cancelAppointmentByDoctor(int appointmentId) {
        Appointment appointment = appointmentRepository.findAppointmentByAppointmentId(appointmentId);

        if (appointment == null) {
            throw new RuntimeException("Appointment not found");
        }
if (appointment.getAppointmentStatus().equals("CANCELLED")) {
throw new RuntimeException("Appointment is already cancelled");}
else {
    appointment.setAppointmentStatus("CANCELLED");
}
        // Update status to CANCELLED
        appointmentRepository.save(appointment);

        // Send cancellation notification to patient
        String patientEmail = appointment.getPatientEmail();
        String patientName = appointment.getPatientName();
        String doctorName = appointment.getDoctorName();
        String doctorEmail= appointment.getDoctorEmail();
        String appointmentDateTime = appointment.getAppointmentDateTime().toString();

        notificationClient.notifyAppointmentcancel(patientEmail, patientName, doctorEmail, doctorName, appointmentDateTime);
    }

}
