package com.spring.lab1.doctor.dto;

import com.spring.lab1.doctor.entity.Doctor;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetDoctorResponse {

    private Long id;

    private String name;

    private String surname;

    private int age;

    private String specialization;

    public static Function<Doctor, GetDoctorResponse> entityToDtoMapper() {
        return doctor -> GetDoctorResponse.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .age(doctor.getAge())
                .specialization(doctor.getSpecialization().getName())
                .build();
    }
}
