package com.quickcare.doctor_service.repositories;

import com.quickcare.doctor_service.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findDoctorsBySpecialization(String specialization);
    List<Doctor> findDoctorsByAvailability(Boolean availability);

    Doctor findDoctorsByDoctorId(long doctorId);
}
