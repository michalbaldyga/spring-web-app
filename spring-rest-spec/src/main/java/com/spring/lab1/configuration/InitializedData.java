package com.spring.lab1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.spring.lab1.specialization.entity.Specialization;
import com.spring.lab1.specialization.service.SpecializationService;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final SpecializationService specializationService;

    @Autowired
    public InitializedData(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @PostConstruct
    private synchronized void init() {
        Specialization anaesthetist = Specialization.builder()
                .name("Anaesthetist")
                .years_of_preparation(3)
                .type("medical")
                .difficulty_level(5)
                .build();
        Specialization orthodontist = Specialization.builder()
                .name("Orthodontist")
                .years_of_preparation(2)
                .type("dental")
                .difficulty_level(3)
                .build();
        Specialization surgeon = Specialization.builder()
                .name("Surgeon")
                .years_of_preparation(4)
                .type("medical")
                .difficulty_level(8)
                .build();

        specializationService.create(anaesthetist);
        specializationService.create(orthodontist);
        specializationService.create(surgeon);

    }

}

