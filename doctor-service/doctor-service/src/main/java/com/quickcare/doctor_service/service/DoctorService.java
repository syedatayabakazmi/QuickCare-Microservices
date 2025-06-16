package com.quickcare.doctor_service.service;

import com.quickcare.doctor_service.entity.Doctor;

import java.util.List;

public interface DoctorService {
    void addDoctor(Doctor doctor);
    Doctor getDoctorById(int id);
    List<Doctor> getAllDoctors();
    Doctor updateDoctor(Doctor doctor);
    void deleteDoctor(Doctor doctor);

}
