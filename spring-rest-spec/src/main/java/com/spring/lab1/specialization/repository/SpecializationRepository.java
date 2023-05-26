package com.spring.lab1.specialization.repository;

import com.spring.lab1.specialization.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface SpecializationRepository extends JpaRepository<Specialization, String> {

}
