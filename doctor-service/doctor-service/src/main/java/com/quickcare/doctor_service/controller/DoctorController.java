package com.quickcare.doctor_service.controller;

import com.quickcare.doctor_service.entity.Doctor;
import com.quickcare.doctor_service.repositories.DoctorRepository;
import com.quickcare.doctor_service.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
     DoctorRepository doctorRepository;

    @PostMapping("/addDoctor")
    void addDoctor(@RequestBody Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @GetMapping("/getAllDoctors")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @GetMapping("/getDoctor/{doctorId}")
    public Doctor getDoctorById(@PathVariable int doctorId) {
        return doctorRepository.findDoctorsByDoctorId(doctorId);
    }

    @PutMapping("/updateDoctor")
    public void updateDoctor(@RequestBody Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @GetMapping("/getdoctorbyspecialization/{specialization}")
    public List<Doctor> getDoctorBySpecialization(@PathVariable String specialization) {
        return doctorRepository.findDoctorsBySpecialization(specialization);
    }

    @GetMapping("/getdoctorbyavailability/{availability}")
    public List<Doctor> getDoctorByAvailability(@PathVariable Boolean availability) {
        return doctorRepository.findDoctorsByAvailability(availability);
    }
}
