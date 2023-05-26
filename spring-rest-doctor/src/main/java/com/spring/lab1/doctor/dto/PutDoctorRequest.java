package com.spring.lab1.doctor.dto;

import com.spring.lab1.doctor.entity.Doctor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutDoctorRequest {
    private String name;
    private String surname;
    private int age;
    private String specialization;

    public static BiFunction<Doctor, PutDoctorRequest, Doctor> dtoToEntityUpdater() {
        return (doctor, request) -> {
            doctor.setName(request.getName());
            doctor.setSurname(request.getSurname());
            doctor.setAge(request.getAge());
            return doctor;
        };
    }
}
