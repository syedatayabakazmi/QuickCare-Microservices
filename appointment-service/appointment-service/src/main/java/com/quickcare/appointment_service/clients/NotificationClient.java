package com.quickcare.appointment_service.clients;

import com.quickcare.appointment_service.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "notification-service", configuration = FeignClientConfig.class)
public interface NotificationClient {

    @PostMapping("/notify/appointment")
    String notifyAppointment(
            @RequestParam("patientEmail") String patientEmail,
            @RequestParam("patientName") String patientName,
            @RequestParam("doctorEmail") String doctorEmail,
            @RequestParam("doctorName") String doctorName,
            @RequestParam("appointmentDateTime") String appointmentDateTime
    );

    @PutMapping("/notify/appointmentcancel")
    String notifyAppointmentcancel(
            @RequestParam("patientEmail") String patientEmail,
            @RequestParam("patientName") String patientName,
            @RequestParam("doctorEmail") String doctorEmail,
            @RequestParam("doctorName") String doctorName,
            @RequestParam("appointmentDateTime") String appointmentDateTime
    );
}
