package com.spring.lab1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.spring.lab1.doctor.entity.Doctor;
import com.spring.lab1.specialization.entity.Specialization;
import com.spring.lab1.doctor.service.DoctorService;
import com.spring.lab1.specialization.service.SpecializationService;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final DoctorService doctorService;

    private final SpecializationService specializationService;

    @Autowired
    public InitializedData(DoctorService doctorService, SpecializationService specializationService) {
        this.doctorService = doctorService;
        this.specializationService = specializationService;
    }

    @PostConstruct
    private synchronized void init() {
        Specialization anaesthetist = Specialization.builder()
                .name("Anaesthetist")
                .build();
        Specialization orthodontist = Specialization.builder()
                .name("Orthodontist")
                .build();
        Specialization surgeon = Specialization.builder()
                .name("Surgeon")
                .build();

        specializationService.create(anaesthetist);
        specializationService.create(orthodontist);
        specializationService.create(surgeon);

        Doctor d1 = Doctor.builder()
                .name("Andrew")
                .surname("Tate")
                .age(34)
                .specialization(anaesthetist)
                .build();

        Doctor d2 = Doctor.builder()
                .name("Michael")
                .surname("Angelo")
                .age(548)
                .specialization(orthodontist)
                .build();

        Doctor d3 = Doctor.builder()
                .name("Giorgio")
                .surname("Giovanni")
                .age(83)
                .specialization(surgeon)
                .build();

        Doctor d4 = Doctor.builder()
                .name("Pablo")
                .surname("Escobar")
                .age(46)
                .specialization(surgeon)
                .build();

        doctorService.create(d1);
        doctorService.create(d2);
        doctorService.create(d3);
        doctorService.create(d4);
    }

}

