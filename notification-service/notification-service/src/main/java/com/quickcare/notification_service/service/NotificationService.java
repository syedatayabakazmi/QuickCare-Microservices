package com.quickcare.notification_service.service;

import com.quickcare.notification_service.dto.NotificationRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailNotification(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("tayabatabbasum1@gmail.com"); // must match configured email
        mailSender.send(message);
    }

    public void notifyAppointmentBooking(NotificationRequestDto notificationRequestDto) {
        // Email for Patient
        sendEmailNotification(notificationRequestDto.getPatientEmail(), "Appointment Confirmed",
                "Dear " + notificationRequestDto.getPatientName() + ", your appointment with Dr. " + notificationRequestDto.getDoctorName() + " is confirmed for " + notificationRequestDto.getAppointmentDateTime());

        // Email for Doctor
        sendEmailNotification(notificationRequestDto.getDoctorEmail(), "New Appointment Scheduled",
                "Dear Dr. " + notificationRequestDto.getDoctorName() + ", you have a new appointment with " + notificationRequestDto.getPatientName() + " on " + notificationRequestDto.getAppointmentDateTime());
    }


    public void notifyAppointmentCancellation(NotificationRequestDto notificationRequestDto) {
        // Email for Patient
        sendEmailNotification(notificationRequestDto.getPatientEmail(), "Appointment Canceled",
                "Dear " + notificationRequestDto.getPatientName() + ", your appointment with Dr. " + notificationRequestDto.getDoctorName() + " has been cancelled for " + notificationRequestDto.getAppointmentDateTime());

        // Email for Doctor
        sendEmailNotification(notificationRequestDto.getDoctorEmail(), "Appointment Canceled",
                "Dear Dr. " + notificationRequestDto.getDoctorName() + ", you have cancelled appointment with " +notificationRequestDto.getPatientName() + " on " + notificationRequestDto.getAppointmentDateTime());
    }
}
