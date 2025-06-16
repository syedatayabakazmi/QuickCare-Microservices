package com.quickcare.patient_service.controller;

import com.quickcare.patient_service.entity.Patient;
import com.quickcare.patient_service.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/addpatient")
    public Patient addPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    @GetMapping("/getpatients")
    public List<Patient> getPatients() {

        return patientService.getAllPatients();
    }

    @GetMapping("/getPatient/{id}")
    public Patient getPatient(@PathVariable int id) {
      return patientService.getPatientById(id);

    }
    @DeleteMapping("/deletepatient/{id}")
    public void deletePatient( @PathVariable int id) {
        patientService.deletePatient(patientService.getPatientById(id));
    }

    @PutMapping("/updatepatient")
    public Patient updatePatient(@RequestBody Patient patient) {
       return patientService.updatePatient(patient);
    }
}

