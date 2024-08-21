package com.healthmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    @NotBlank(message = "Doctor Name should not be empty")
    @Pattern(regexp = "^[A-Za-z]+$",message = "Doctor Name should contain only Alphabets")
    @Size(min = 2, max = 200, message = "Doctor Name size should be between 2 and 200 only")
    private String doctorName;

    @NotBlank(message = "City Name should not be empty")
    @Pattern(regexp = "^(Hyderabad|Banglore|Chennai|Mumbai|Kolkata)$",message = "City name should be Hyderabad/Banglore/Chennai/Mumbai/Kolkata")
    private String city;

    @Email(message = "Email is invalid")
    @Column(unique = true)
    private String email;

    @Size(min = 10,max = 10,message = "Phone Number should be 10 digits")
    private String phoneNumber;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "doctor_speciality",
    joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns =@JoinColumn(name = "speciality_id") )
    private List<Speciality> specialities = new ArrayList<>();
}
