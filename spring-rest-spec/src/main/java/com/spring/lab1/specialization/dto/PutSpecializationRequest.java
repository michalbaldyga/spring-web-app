package com.spring.lab1.specialization.dto;

import com.spring.lab1.specialization.entity.Specialization;
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
public class PutSpecializationRequest {
    private int years_of_preparation;
    private String type;
    private int difficulty_level;

    public static BiFunction<Specialization, PutSpecializationRequest, Specialization> dtoToEntityUpdater() {
        return (specialization, request) -> {
            specialization.setYears_of_preparation(request.getYears_of_preparation());
            specialization.setType(request.getType());
            specialization.setDifficulty_level(request.getDifficulty_level());
            return specialization;
        };
    }
}
