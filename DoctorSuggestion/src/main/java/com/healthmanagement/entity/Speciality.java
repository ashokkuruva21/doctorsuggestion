package com.healthmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Speciality should not be Empty")
    @Pattern(regexp = "^(Cardiology|Neurology|Dermatology|" +
            "Orthopedics|Pediatrics|Ophthalmology|Psychiatry" +
            "|Gastroenterology|Endocrinology|Pulmonology)$",
            message = "Invalid Speciality")
    private String specialityName;

}
