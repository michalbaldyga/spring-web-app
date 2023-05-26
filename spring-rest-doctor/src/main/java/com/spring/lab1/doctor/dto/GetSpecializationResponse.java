package com.spring.lab1.doctor.dto;

import lombok.*;

import java.util.List;
import java.util.Map;
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

}
