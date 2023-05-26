package com.spring.lab1.doctor.controller;

import com.spring.lab1.doctor.dto.GetDoctorResponse;
import com.spring.lab1.doctor.dto.GetDoctorsResponse;
import com.spring.lab1.doctor.dto.PostDoctorRequest;
import com.spring.lab1.doctor.dto.PutDoctorRequest;
import com.spring.lab1.doctor.entity.Doctor;
import com.spring.lab1.doctor.service.DoctorService;
import com.spring.lab1.specialization.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/doctors")
public class DoctorController {
    
    private DoctorService doctorService;
    private SpecializationService specializationService;
    
    @Autowired
    public DoctorController(DoctorService doctorService, SpecializationService specializationService) {
        this.doctorService = doctorService;
        this.specializationService = specializationService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})//When empty JSON is default
    public ResponseEntity<GetDoctorsResponse> getDoctors() {
        return ResponseEntity
                .ok(GetDoctorsResponse.entityToDtoMapper().apply(doctorService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetDoctorResponse> getDoctor(@PathVariable("id") long id) {
        return doctorService.find(id)
                .map(value -> ResponseEntity
                        .ok(GetDoctorResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    @PostMapping
    public ResponseEntity<Void> postDoctor(@RequestBody PostDoctorRequest request, UriComponentsBuilder builder) {
        Doctor doctor = PostDoctorRequest
                .dtoToEntityMapper(() -> null)
                .apply(request);
        doctor = doctorService.create(doctor);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "doctors", "{id}")
                        .buildAndExpand(doctor.getId()).toUri())
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("id") long id) {
        Optional<Doctor> doctor = doctorService.find(id);
        if (doctor.isPresent()) {
            doctorService.delete(doctor.get().getId());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putDoctor(@RequestBody PutDoctorRequest request, @PathVariable("id") long id) {
        Optional<Doctor> doctor = doctorService.find(id);
        if (doctor.isPresent()) {
            PutDoctorRequest.dtoToEntityUpdater().apply(doctor.get(), request);
            doctorService.update(doctor.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
