package com.quickcare.notification_service.service;

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

    public void notifyAppointmentBooking(String patientEmail, String patientName, String doctorEmail, String doctorName, String appointmentDateTime) {
        // Email for Patient
        sendEmailNotification(patientEmail, "Appointment Confirmed",
                "Dear " + patientName + ", your appointment with Dr. " + doctorName + " is confirmed for " + appointmentDateTime);

        // Email for Doctor
        sendEmailNotification(doctorEmail, "New Appointment Scheduled",
                "Dear Dr. " + doctorName + ", you have a new appointment with " + patientName + " on " + appointmentDateTime);
    }


    public void notifyAppointmentCancellation(String patientEmail, String patientName, String doctorEmail, String doctorName, String appointmentDateTime) {
        // Email for Patient
        sendEmailNotification(patientEmail, "Appointment Canceled",
                "Dear " + patientName + ", your appointment with Dr. " + doctorName + " has been cancelled for " + appointmentDateTime);

        // Email for Doctor
        sendEmailNotification(doctorEmail, "Appointment Canceled",
                "Dear Dr. " + doctorName + ", you have cancelled appointment with " + patientName + " on " + appointmentDateTime);
    }
}
