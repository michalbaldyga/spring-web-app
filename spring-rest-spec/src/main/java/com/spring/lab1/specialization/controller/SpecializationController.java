package com.spring.lab1.specialization.controller;

import com.spring.lab1.specialization.dto.*;
import com.spring.lab1.specialization.entity.Specialization;
import com.spring.lab1.specialization.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/specializations")
public class SpecializationController {

    private SpecializationService specializationService;

    @Autowired
    public SpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<GetSpecializationsResponse> getSpecializations() {
        return ResponseEntity
                .ok(GetSpecializationsResponse.entityToDtoMapper().apply(specializationService.findAll()));
    }

    @GetMapping("{name}")
    public ResponseEntity<GetSpecializationResponse> getSpecialization(@PathVariable("name") String name) {
        return specializationService.find(name)
                .map(value -> ResponseEntity
                        .ok(GetSpecializationResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    @PostMapping
    public ResponseEntity<Void> postSpecialization(@RequestBody PostSpecializationRequest request, UriComponentsBuilder builder) {
        Specialization specialization = PostSpecializationRequest
                .dtoToEntityMapper()
                .apply(request);
        specializationService.create(specialization);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "specializations", "{name}")
                        .buildAndExpand(specialization.getName()).toUri())
                .build();
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteSpecialization(@PathVariable("name") String name) {
        Optional<Specialization> specialization = specializationService.find(name);
        if (specialization.isPresent()) {
            specializationService.delete(specialization.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> putSpecialization(@RequestBody PutSpecializationRequest request, @PathVariable("name") String name) {
        Optional<Specialization> specialization = specializationService.find(name);
        if (specialization.isPresent()) {
            PutSpecializationRequest.dtoToEntityUpdater().apply(specialization.get(), request);
            specializationService.update(specialization.get());
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
