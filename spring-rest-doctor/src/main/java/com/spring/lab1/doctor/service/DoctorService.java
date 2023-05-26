package com.spring.lab1.doctor.service;

import com.spring.lab1.doctor.entity.Doctor;
import com.spring.lab1.doctor.repository.DoctorRepository;
import com.spring.lab1.specialization.entity.Specialization;
import com.spring.lab1.specialization.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;
    private SpecializationRepository specializationRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, SpecializationRepository specializationRepository) {
        this.doctorRepository = doctorRepository;
        this.specializationRepository = specializationRepository;
    }

    public Optional<Doctor> find(Long id) {
        return doctorRepository.findById(id);
    }

    public Optional<Doctor> find(String specializationName, Long id) {
        Optional<Specialization> specialization = specializationRepository.findById(specializationName);
        if (specialization.isPresent()) {
            return doctorRepository.findByIdAndSpecialization(id, specialization.get());
        }
        else {
            return Optional.empty();
        }
    }

    public List<Doctor> findAll(Specialization specialization) {
        return doctorRepository.findAllBySpecialization(specialization);
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Transactional
    public Doctor create(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Transactional
    public void update(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Transactional
    public void delete(Long doctor) {
        doctorRepository.deleteById(doctor);
    }
}
