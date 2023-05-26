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

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetSpecializationResponse {

    private String name;
    private int years_of_preparation;
    private String type;
    private int difficulty_level;

    public static Function<Specialization, GetSpecializationResponse> entityToDtoMapper() {
        return specialization -> GetSpecializationResponse.builder()
                .name(specialization.getName())
                .years_of_preparation(specialization.getYears_of_preparation())
                .type(specialization.getType())
                .difficulty_level(specialization.getDifficulty_level())
                .build();
    }
}
