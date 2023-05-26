package com.spring.lab1.doctor.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetDoctorsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Doctor {
        private Long id;
        private String name;
        private String surname;
        private int age;
        private String specialization;
    }

    @Singular
    private List<Doctor> doctors;

    public static Function<Collection<com.spring.lab1.doctor.entity.Doctor>, GetDoctorsResponse> entityToDtoMapper() {
        return doctors -> {
            GetDoctorsResponseBuilder response = GetDoctorsResponse.builder();
            doctors.stream()
                    .map(doctor -> Doctor.builder()
                            .id(doctor.getId())
                            .name(doctor.getName())
                            .surname(doctor.getSurname())
                            .age(doctor.getAge())
                            .specialization(doctor.getSpecialization().getName())
                            .build())
                    .forEach(response::doctor);
            return response.build();
        };
    }
}
