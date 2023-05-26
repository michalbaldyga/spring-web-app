package com.spring.lab1.specialization.service;

import com.spring.lab1.specialization.entity.Specialization;
import com.spring.lab1.specialization.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SpecializationService {

    private SpecializationRepository repository;


    @Autowired
    public SpecializationService(SpecializationRepository repository) {
        this.repository = repository;
    }

    public Optional<Specialization> find(String name) {
        return repository.findById(name);
    }

    public List<Specialization> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Specialization create(Specialization specialization) {
        return repository.save(specialization);
    }

    @Transactional
    public void update(Specialization specialization) {
        repository.save(specialization);
    }

    @Transactional
    public void delete(Specialization specialization) {
        repository.delete(specialization);
    }
}
