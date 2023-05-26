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
public class PostSpecializationRequest {
    private String name;
    private int years_of_preparation;
    private String type;
    private int difficulty_level;

    public static Function<PostSpecializationRequest, Specialization> dtoToEntityMapper() {
        return request -> Specialization.builder()
                .name(request.getName())
                .years_of_preparation(request.getYears_of_preparation())
                .type(request.getType())
                .difficulty_level(request.getDifficulty_level())
                .build();
    }
}
