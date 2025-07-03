package com.quickcare.notification_service.dto;

import lombok.Data;

@Data
public class NotificationRequestDto {
    private String patientEmail;
    private String patientName;
    private String doctorEmail;
    private String doctorName;
    private String appointmentDateTime;

}
