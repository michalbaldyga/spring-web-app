package com.spring.lab1.doctor.dto;

import com.spring.lab1.doctor.entity.Doctor;
import com.spring.lab1.specialization.entity.Specialization;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostDoctorRequest {
    private String name;
    private String surname;
    private int age;
    private String specialization;

    public static Function<PostDoctorRequest, Doctor> dtoToEntityMapper(Supplier<Specialization> specializationSupplier) {
        return request -> Doctor.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .age(request.getAge())
                .specialization(specializationSupplier.get())
                .build();
    }

}
