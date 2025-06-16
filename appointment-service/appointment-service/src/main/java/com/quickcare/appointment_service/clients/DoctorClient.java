package com.quickcare.appointment_service.clients;

import com.quickcare.appointment_service.config.FeignClientConfig;
import com.quickcare.appointment_service.dto.Doctor;
import com.quickcare.appointment_service.dto.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "doctor-service", configuration = FeignClientConfig.class)
public interface DoctorClient {
    @GetMapping("/doctor/getDoctor/{doctorId}")
    Doctor getDoctorById(@PathVariable int doctorId);
}
