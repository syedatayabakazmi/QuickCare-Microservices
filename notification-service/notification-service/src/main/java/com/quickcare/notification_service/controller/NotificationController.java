package com.quickcare.notification_service.controller;

import com.quickcare.notification_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/appointment")
    public ResponseEntity<String> notifyAppointment(
            @RequestParam String patientEmail,
            @RequestParam String patientName,
            @RequestParam String doctorEmail,
            @RequestParam String doctorName,
            @RequestParam String appointmentDateTime) {

        notificationService.notifyAppointmentBooking(
                patientEmail, patientName, doctorEmail, doctorName, appointmentDateTime
        );

        return ResponseEntity.ok("Notification emails sent to patient and doctor.");
    }


    @PutMapping("/appointmentcancel")
    public ResponseEntity<String> notifyAppointmentCancel(
            @RequestParam String patientEmail,
            @RequestParam String patientName,
            @RequestParam String doctorEmail,
            @RequestParam String doctorName,
            @RequestParam String appointmentDateTime) {

        notificationService.notifyAppointmentCancellation(
                patientEmail, patientName, doctorEmail, doctorName, appointmentDateTime
        );

        return ResponseEntity.ok("Notification emails sent to patient and doctor.");
    }
}
