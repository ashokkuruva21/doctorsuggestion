package com.healthmanagement.controller;

import com.healthmanagement.entity.Doctor;
import com.healthmanagement.exception.ErrorMessage;
import com.healthmanagement.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@Tag(name = "Doctor API", description = "Operations related to Doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Operation(summary = "To create the new Doctor")
    @ApiResponse(responseCode = "201", description = "Doctor is created successfully")
    @PostMapping("/register")
    public ResponseEntity<Doctor> createDoctor(@Valid @RequestBody Doctor doctor){
        Doctor createdDoctor = doctorService.registerDoctor(doctor);
        return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
    }
    @Operation(summary = "Get the Doctor by using Doctor Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Doctor details are successfully found"),
                          @ApiResponse(responseCode = "404",description = "Doctor Id is Invalid",content = @Content(schema = @Schema(implementation = ErrorMessage.class)))})
    @GetMapping("/get/{id}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable(name = "id") Long doctorId){
        Doctor doctorById = doctorService.getDoctorById(doctorId);
        return new ResponseEntity<>(doctorById,HttpStatus.OK);
    }

    @Operation(summary = "To get all the Doctors List")
    @ApiResponse(responseCode = "200",description = "Fetched the Doctors List successfully")
    @GetMapping("/all")
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        List<Doctor> allDoctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(allDoctors,HttpStatus.OK);
    }

    @Operation(summary = "To Update the Doctor based on Doctor Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Doctor is updated successfully"),
                           @ApiResponse(responseCode = "404",description = "Doctor Id is Invalid",content = @Content(schema = @Schema(implementation = ErrorMessage.class)))})
    @PutMapping("/update/{doctorId}")
    public ResponseEntity<Doctor> updateDoctor(@Valid @RequestBody Doctor doctor,@PathVariable Long doctorId){
        Doctor updatedDoctor = doctorService.updateDoctorByDoctorId(doctor, doctorId);
        return new ResponseEntity<>(updatedDoctor,HttpStatus.OK);
    }

    @Operation(summary = "To Delete the Doctor based on Doctor Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Doctor is deleted successfully"),
                           @ApiResponse(responseCode = "404",description = "Doctor Id is Invalid",content = @Content(schema = @Schema(implementation = ErrorMessage.class)))})
    @DeleteMapping("/delete/{doctorId}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long doctorId){
        String response = doctorService.DeleteDoctorById(doctorId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
