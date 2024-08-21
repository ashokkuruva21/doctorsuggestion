package com.healthmanagement.controller;

import com.healthmanagement.entity.Doctor;
import com.healthmanagement.service.SuggestDoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/suggest")
@Tag(name = "SuggestDoctor API", description = "Operations related to SuggestDoctor")
public class SuggestDoctorController {

    @Autowired
    private SuggestDoctorService suggestDoctorService;

    @Operation(summary = "To get the suggestion for the patient based on their symptoms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Doctor is suggested successfully based on the symptoms of the patient"),
            @ApiResponse(responseCode = "404",description = "Doctor is unavailable for the symptoms of the Patient")
    })
    @GetMapping("/doctor/{patientId}")
    public List<Doctor> suggestDoctorsByPatientId(@PathVariable Long patientId){
        List<Doctor> doctors = suggestDoctorService.suggestDoctor(patientId);
        return doctors;
    }

    @Operation(summary = "To get the suggestion for the patient based on their symptoms in the same city of the patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Doctor is suggested successfully based on the symptoms of the patient in the same city of the patient"),
            @ApiResponse(responseCode = "404",description = "Doctor is unavailable for the symptoms of the Patient in the same city")
    })
    @GetMapping("/doctorInCity/{patientId}")
    public List<Doctor> suggestDoctorInSameCity(@PathVariable Long patientId){
        List<Doctor> doctors=suggestDoctorService.doctorsBasedOnSymptomsAndCity(patientId);
        return doctors;
    }



}
