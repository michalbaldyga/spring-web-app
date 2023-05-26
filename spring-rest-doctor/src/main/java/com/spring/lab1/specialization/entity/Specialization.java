package com.spring.lab1.specialization.entity;

import com.spring.lab1.doctor.entity.Doctor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "specializations")
public class Specialization {

    @Id
    private String name;

    @OneToMany(mappedBy = "specialization", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Doctor> doctors;

}
