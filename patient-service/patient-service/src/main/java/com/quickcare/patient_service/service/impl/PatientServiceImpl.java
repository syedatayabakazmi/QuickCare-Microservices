package com.quickcare.patient_service.service.impl;

import com.quickcare.patient_service.entity.Patient;
import com.quickcare.patient_service.repositories.PatientRepository;
import com.quickcare.patient_service.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;


    @Override
    public Patient addPatient(Patient patient) {
        patientRepository.save(patient);
        return patient;
    }

    @Override
    public Patient updatePatient(Patient patient) {
        System.out.println("Updating patient with ID: " + patient.getId());

        if (!patientRepository.existsById(patient.getId())) {
            throw new RuntimeException("Patient ID not found: " + patient.getId());
        }

        return patientRepository.save(patient);
    }


    @Override
    public void deletePatient(Patient patient) {
        patientRepository.delete(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
       return patientRepository.findAll();

    }

    @Override
    public Patient getPatientById(int id) {

        return patientRepository.getPatientById(id);
    }
}
