package com.spring.lab1.specialization.event.dto;

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

    public static Function<Specialization, PostSpecializationRequest> entityToDtoMapper() {
        return entity -> PostSpecializationRequest.builder()
                .name(entity.getName())
                .build();
    }
}
