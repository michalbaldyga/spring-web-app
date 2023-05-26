package com.spring.lab1.specialization.dto;

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
public class GetSpecializationsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Specialization {
        private String name;
        private int years_of_preparation;
        private String type;
        private int difficulty_level;
    }

    @Singular
    private List<GetSpecializationsResponse.Specialization> specializations;

    public static Function<Collection<com.spring.lab1.specialization.entity.Specialization>, GetSpecializationsResponse> entityToDtoMapper() {
        return specializations -> {
            GetSpecializationsResponse.GetSpecializationsResponseBuilder response = GetSpecializationsResponse.builder();
            specializations.stream()
                    .map(specialization -> Specialization.builder()
                            .name(specialization.getName())
                            .years_of_preparation(specialization.getYears_of_preparation())
                            .type(specialization.getType())
                            .difficulty_level(specialization.getDifficulty_level())
                            .build())
                    .forEach(response::specialization);
            return response.build();
        };
    }
}
