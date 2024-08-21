package com.healthmanagement.controller;

import com.healthmanagement.entity.Patient;
import com.healthmanagement.exception.ErrorMessage;
import com.healthmanagement.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Operation(summary = "To create the new Patient")
    @ApiResponse(responseCode = "201",description = "Patient is created successfully")
    @PostMapping("/register")
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient){
        Patient createdPatient = patientService.registerPatient(patient);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }

    @Operation(summary = "To get the Patient based on Patient Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Patient is fecthed successfully"),
            @ApiResponse(responseCode = "404",description = "Patient Id is Invalid",content = @Content(schema = @Schema(implementation = ErrorMessage.class)))})
    @GetMapping("/get")
    public ResponseEntity<Patient> getPatient(@PathParam("patientId") Long patientId){
        Patient patientById = patientService.getPatientById(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(patientById);
    }

    @Operation(summary = "To get all the Patients")
    @ApiResponse(responseCode = "200",description = "All the Patients are fetched successfully")
    @GetMapping("/allPatients")
    public ResponseEntity<List<Patient>> getAllPatients(){
        List<Patient> allPatients = patientService.getAllPatients();
        return ResponseEntity.status(HttpStatus.OK).body(allPatients);
    }

    @Operation(summary = "To Update the Patient based on Patient Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Patient is updated successfully"),
            @ApiResponse(responseCode = "404",description = "Patient Id is Invalid",content = @Content(schema = @Schema(implementation = ErrorMessage.class)))})
    @PutMapping("/update")
    public ResponseEntity<Patient> updatePatient(@Valid @RequestBody Patient patient, @PathParam("patientId") Long patientId){
        Patient updatedPatient = patientService.updatePatientByPatientId(patient, patientId);
        return new ResponseEntity<>(updatedPatient,HttpStatus.OK);
    }

    @Operation(summary = "To delete the Patient based on Patient Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Patient is deleted successfully"),
            @ApiResponse(responseCode = "404",description = "Patient Id is Invalid",content = @Content(schema = @Schema(implementation = ErrorMessage.class)))})
    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<String> deletePatient(@PathVariable Long patientId){
        String response = patientService.deletePatientById(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
