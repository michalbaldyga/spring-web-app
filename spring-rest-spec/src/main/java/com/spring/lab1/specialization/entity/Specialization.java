package com.spring.lab1.specialization.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "specializations")
public class Specialization {

    /**
     * Specialization name.
     */
    @Id
    private String name;

    /**
     * Required number of years of preparation to obtain the specialization.
     */
    private int years_of_preparation;

    /**
     * Specialization type: medical, dental or veterinary
     */
    private String type;

    /**
     * Level of difficulty of the specialization.
     */
    private int difficulty_level;

}
