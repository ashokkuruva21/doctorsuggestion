package com.healthmanagement.controller;

import com.healthmanagement.entity.Speciality;
import com.healthmanagement.exception.ErrorMessage;
import com.healthmanagement.repository.SpecialityRepository;
import com.healthmanagement.service.SpecialityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialities")
public class SpecialityController {


    @Autowired
    private SpecialityService specialityService;

    @Operation(summary = "To create the Specialist")
    @ApiResponse(responseCode = "201",description = "Specialist is created successfully")
    @PostMapping
    public ResponseEntity<Speciality> createSpeciality(@Valid @RequestBody Speciality speciality){
        Speciality createdSpeciality = specialityService.registerSpeciality(speciality);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpeciality);

    }

    @Operation(summary = "To get all the Specialities")
    @ApiResponse(responseCode = "200",description = "Specialities List is fetched successfully")
    @GetMapping
    public ResponseEntity<List<Speciality>> getALlSpecialties(){
        List<Speciality> allSpecialities = specialityService.getAllSpecialities();
        return new ResponseEntity<>(allSpecialities,HttpStatus.OK);
    }

    @Operation(summary = "To get the Speciality based on the speciality Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Speciality is found successfully"),
            @ApiResponse(responseCode = "404",description = "Speciality Id is Invalid", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping("/{specialityId}")
    public ResponseEntity<Speciality> getSpeciality(@PathVariable("specialityId") Long specialityId){
        Speciality specialityById = specialityService.getSpecialityById(specialityId);
        return new ResponseEntity<>(specialityById,HttpStatus.OK);
    }

    @Operation(summary = "To update the Speciality based on the speciality Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Speciality is updated successfully"),
            @ApiResponse(responseCode = "404",description = "Speciality Id is Invalid", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PutMapping("/update/{specialityId}")
    public ResponseEntity<Speciality> updateSpeciality(@Valid @RequestBody Speciality speciality,@PathVariable Long specialityId){
        Speciality updatedSpeciality = specialityService.updateSpeciality(speciality, specialityId);
        return new ResponseEntity<>(updatedSpeciality,HttpStatus.OK);
    }


    @Operation(summary = "To delete the Speciality based on the speciality Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Speciality is deleted successfully"),
            @ApiResponse(responseCode = "404",description = "Speciality Id is Invalid", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    @DeleteMapping("/delete/{specialityId}")
    public ResponseEntity<String> deleteSpeciality(@PathVariable Long specialityId){
        String response=specialityService.deleteSpeciality(specialityId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
