package com.quickcare.doctor_service.service.impl;

import com.quickcare.doctor_service.entity.Doctor;
import com.quickcare.doctor_service.repositories.DoctorRepository;
import com.quickcare.doctor_service.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;

    @Override
    public void addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctorById(int id) {
        return doctorRepository.findDoctorsByDoctorId(id);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(Doctor doctor) {
        doctorRepository.delete(doctor);
    }
}
