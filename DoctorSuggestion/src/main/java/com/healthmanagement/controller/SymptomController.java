package com.healthmanagement.controller;

import com.healthmanagement.entity.Symptom;
import com.healthmanagement.service.SymptomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/symptoms")
@Tag(name = "Symptom API", description = "Operations related to Symptom")
public class SymptomController {

    @Autowired
    private SymptomService symptomService;

    @GetMapping("/")
    public ResponseEntity<List<Symptom>> getAllSymptoms(){
        List<Symptom> allSymptoms = symptomService.getAllSymptoms();
        return new ResponseEntity<>(allSymptoms, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Symptom> createSymptom(@Valid @RequestBody Symptom symptom){
        Symptom createdSymptom=symptomService.registerSymptom(symptom);
        return new ResponseEntity<>(createdSymptom,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Symptom> getSymptomById(@PathParam("symptomId") Long symptomId){
        Symptom symptomById = symptomService.getSymptomById(symptomId);
        return new ResponseEntity<>(symptomById,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSymptom(@PathParam("symptomId") Long symptomId){
        String response=symptomService.deleteSymptom(symptomId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Symptom> updateSymptom(@Valid @RequestBody Symptom symptom,@PathParam("symptomId") Long symptomId){
        Symptom updatedSymptom = symptomService.updateSymptom(symptom, symptomId);
        return new ResponseEntity<>(updatedSymptom,HttpStatus.OK);
    }
}
