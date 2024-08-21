package com.healthmanagement.entity;

import jakarta.persistence.*;
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
public class Symptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long symptomId;

    @Pattern(regexp = "^(Chest Pain|Headache|Rash|Joint Pain|Fever|" +
            "Blurred Vision|Anxiety|Abdominal Pain|Fatigue|Shortness of Breath)$",
    message = "Invalid Symptom")
    private String symptomDescription;



   /*
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "symptom_speciality",
    joinColumns = @JoinColumn(name = "symptomId"),
    inverseJoinColumns = @JoinColumn(name = "specialityId"))
    private List<Speciality> specialities= new ArrayList<>();

    */
}
