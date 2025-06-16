package com.quickcare.appointment_service.controller;

import com.quickcare.appointment_service.clients.DoctorClient;
import com.quickcare.appointment_service.clients.PatientClient;
import com.quickcare.appointment_service.dto.Doctor;
import com.quickcare.appointment_service.dto.Patient;
import com.quickcare.appointment_service.entity.Appointment;
import com.quickcare.appointment_service.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private PatientClient patientClient;

@Autowired
private AppointmentService appointmentService;
    @Autowired
    private DoctorClient doctorClient;

    @PreAuthorize("hasAuthority('ROLE_PATIENT')")
    @PostMapping("bookAppointment")
    public ResponseEntity<String> bookAppointment(@RequestBody Appointment appointment) {
        appointmentService.bookAppointment(appointment);
        return ResponseEntity.ok("Appointment booked successfully.");
    }


    @GetMapping("/getAllAppointments")
    List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/getappointmentbypatientid/{patientId}")
    public List<Appointment> getAppointmentsByPatientId(@PathVariable("patientId") int patientId) {
        // Step 1: Verify patient exists via patient service
        Patient patient = patientClient.getPatientById(patientId);
        if (patient == null) {
            throw new RuntimeException("Patient not found with ID: " + patientId);
        }

        // Step 2: Fetch appointments from local appointment DB
        return appointmentService.getAppointmentByPatientId(patientId);
    }

    @GetMapping("/getappointmentbydoctorid/{doctorId}")
    public List<Appointment> getAppointmentsByDoctorId(@PathVariable int doctorId) {
        // Step 1: Verify patient exists via patient service
        Doctor doctor = doctorClient.getDoctorById(doctorId);
        if (doctor == null) {
            throw new RuntimeException("doctor not found with ID: " + doctorId);
        }

        // Step 2: Fetch appointments from local appointment DB
        return appointmentService.getAppointmentByDoctorId(doctorId);
    }

    @DeleteMapping("/deleteAppointment/{appointmentId}")
    void deleteAppointment(@PathVariable int appointmentId) {
        appointmentService.cancelAppointment(appointmentService.findAppointmentById(appointmentId));
    }

    @PutMapping("/updateAppointment")
    void updateAppointment(@RequestBody Appointment appointment) {
        appointmentService.updateAppointment(appointment);
    }

    @GetMapping("/getAppointmentById/{appointmentId}")
    Appointment getAppointmentById(@PathVariable int appointmentId) {
        return appointmentService.findAppointmentById(appointmentId);
    }


    @PutMapping("/cancelAppointmentByDoctor/{appointmentId}")
    public ResponseEntity<String> cancelAppointmentByDoctor(@PathVariable int appointmentId) {
        appointmentService.cancelAppointmentByDoctor(appointmentId);
        return ResponseEntity.ok("Appointment cancelled and patient notified.");
    }

}
