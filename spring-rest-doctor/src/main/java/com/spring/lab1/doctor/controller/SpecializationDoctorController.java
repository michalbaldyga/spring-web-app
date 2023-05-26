package com.spring.lab1.doctor.controller;


import com.spring.lab1.doctor.dto.GetDoctorResponse;
import com.spring.lab1.doctor.dto.GetDoctorsResponse;
import com.spring.lab1.doctor.dto.PostDoctorRequest;
import com.spring.lab1.doctor.dto.PutDoctorRequest;
import com.spring.lab1.doctor.entity.Doctor;
import com.spring.lab1.doctor.service.DoctorService;
import com.spring.lab1.specialization.entity.Specialization;
import com.spring.lab1.specialization.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.Optional;

@RestController
@RequestMapping("api/specializations/{name}/doctors")
public class SpecializationDoctorController {

    private DoctorService doctorService;

    private SpecializationService specializationService;

    @Autowired
    public SpecializationDoctorController(DoctorService doctorService, SpecializationService specializationService) {
        this.doctorService = doctorService;
        this.specializationService = specializationService;
    }

    @GetMapping
    public ResponseEntity<GetDoctorsResponse> getDoctors(@PathVariable("name") String name) {
        Optional<Specialization> specialization = specializationService.find(name);
        return specialization.map(value -> ResponseEntity.ok(GetDoctorsResponse.entityToDtoMapper().apply(doctorService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetDoctorResponse> getDoctor(@PathVariable("name") String name,
                                                       @PathVariable("id") long id) {
        return doctorService.find(name, id)
                .map(value -> ResponseEntity.ok(GetDoctorResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postDoctor(@PathVariable("name") String namee,
                                              @RequestBody PostDoctorRequest request,
                                              UriComponentsBuilder builder) {
        Optional<Specialization> specialization = specializationService.find(namee);
        if (specialization.isPresent()) {
            Doctor doctor = PostDoctorRequest
                    .dtoToEntityMapper(specialization::get)
                    .apply(request);
            doctor = doctorService.create(doctor);
            return ResponseEntity.created(builder.pathSegment("api", "specializations", "{name}", "doctors", "{id}")
                    .buildAndExpand(specialization.get().getName(), doctor.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("name") String name,
                                                @PathVariable("id") long id) {
        Optional<Doctor> doctor = doctorService.find(name, id);
        if (doctor.isPresent()) {
            doctorService.delete(doctor.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putDoctor(@PathVariable("name") String name,
                                             @RequestBody PutDoctorRequest request,
                                             @PathVariable("id") long id) {
        Optional<Doctor> doctor = doctorService.find(name, id);
        if (doctor.isPresent()) {
            PutDoctorRequest.dtoToEntityUpdater().apply(doctor.get(), request);
            doctorService.update(doctor.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
