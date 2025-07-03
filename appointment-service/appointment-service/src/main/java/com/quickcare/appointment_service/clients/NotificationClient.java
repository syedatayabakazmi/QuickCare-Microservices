package com.quickcare.appointment_service.clients;

import com.quickcare.appointment_service.config.FeignClientConfig;
import com.quickcare.appointment_service.dto.NotificationRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "notification-service", configuration = FeignClientConfig.class)
public interface NotificationClient {

    @PostMapping("/notify/appointment")
    String notifyAppointment(@RequestBody NotificationRequestDto notificationRequestDto
                             );

    @PutMapping("/notify/appointmentcancel")
    String notifyAppointmentcancel(
            @RequestBody NotificationRequestDto notificationRequestDto
    );
}
