package com.quickcare.patient_service.service;

import com.quickcare.patient_service.entity.Patient;

import java.util.List;

public interface PatientService {
    Patient addPatient(Patient patient);
    Patient updatePatient(Patient patient);
    void deletePatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientById(int id);
}
