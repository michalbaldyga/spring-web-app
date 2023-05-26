package com.spring.lab1.specialization.controller;

import com.spring.lab1.specialization.dto.PostSpecializationRequest;
import com.spring.lab1.specialization.entity.Specialization;
import com.spring.lab1.specialization.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("")
    public ResponseEntity<Void> postSpecialization(@RequestBody PostSpecializationRequest request, UriComponentsBuilder builder) {
        Specialization specialization = PostSpecializationRequest.dtoToEntityMapper().apply(request);
        specializationService.create(specialization);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "specializations", "{name}")
                        .buildAndExpand(specialization.getName()).toUri())
                .build();
    }

}

