package com.quickcare.notification_service.controller;

import com.quickcare.notification_service.dto.NotificationRequestDto;
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
    public ResponseEntity<String> notifyAppointment(@RequestBody NotificationRequestDto notificationRequestDto) {

        notificationService.notifyAppointmentBooking(
              notificationRequestDto
        );

        return ResponseEntity.ok("Notification emails sent to patient and doctor.");
    }


    @PutMapping("/appointmentcancel")
    public ResponseEntity<String> notifyAppointmentCancel(@RequestBody NotificationRequestDto notificationRequestDto) {

        notificationService.notifyAppointmentCancellation(notificationRequestDto);

        return ResponseEntity.ok("Notification emails sent to patient and doctor.");
    }
}
