package com.spring.lab1.specialization.service;

import com.spring.lab1.specialization.entity.Specialization;
import com.spring.lab1.specialization.event.repository.SpecializationEventRepository;
import com.spring.lab1.specialization.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SpecializationService {

    private SpecializationRepository specializationRepository;

    // Repository for sending events about actions on specialization entities.
    private SpecializationEventRepository specializationEventRepository;


    @Autowired
    public SpecializationService(SpecializationRepository specializationRepository, SpecializationEventRepository specializationEventRepository) {
        this.specializationRepository = specializationRepository;
        this.specializationEventRepository = specializationEventRepository;
    }

    public Optional<Specialization> find(String name) {
        return specializationRepository.findById(name);
    }

    public List<Specialization> findAll() {
        return specializationRepository.findAll();
    }

    @Transactional
    public void create(Specialization specialization) {
        specializationRepository.save(specialization);
        specializationEventRepository.create(specialization);
    }

    @Transactional
    public void update(Specialization specialization) {
        specializationRepository.save(specialization);
    }

    @Transactional
    public void delete(Specialization specialization) {
        specializationRepository.delete(specialization);
        specializationEventRepository.delete(specialization);
    }
}
