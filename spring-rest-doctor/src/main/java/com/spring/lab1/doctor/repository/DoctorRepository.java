package com.spring.lab1.doctor.repository;

import com.spring.lab1.doctor.entity.Doctor;
import com.spring.lab1.specialization.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findAllBySpecialization(Specialization specialization);

    Optional<Doctor> findByIdAndSpecialization(Long id, Specialization specialization);

}
