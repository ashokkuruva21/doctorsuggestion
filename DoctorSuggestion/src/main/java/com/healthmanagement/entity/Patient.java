package com.healthmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @NotBlank(message = "Patient Name should not be empty")
    @Pattern(regexp = "^[A-Za-z]+$",message = "Patient Name should contain only Alphabets")
    @Size(min = 2, max = 200, message = "Patient Name size should be between 2 and 200 only")
    private String patientName;

    @Email(message = "Email is invalid")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "City Name should not be empty")
    @Pattern(regexp = "^(Hyderabad|Banglore|Chennai|Mumbai|Kolkata)$",message = "City name should be Hyderabad/Banglore/Chennai/Mumbai/Kolkata")
    private String city;

    @Size(min = 10,max = 10,message = "Phone Number should be 10 digits")
    private  String phoneNumber;

    @NotNull(message = "Date of Birth should not be Null")
    @PastOrPresent(message = "Date of birth should be earlier date")
    private LocalDate dateOfBirth;


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "patient_symptoms",
    joinColumns = @JoinColumn(name = "patientId"),
    inverseJoinColumns = @JoinColumn(name = "symptomId"))
    private List<Symptom> symptoms=new ArrayList<>();
}
