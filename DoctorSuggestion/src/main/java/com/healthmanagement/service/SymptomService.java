package com.healthmanagement.service;

import com.healthmanagement.entity.Symptom;
import com.healthmanagement.exception.NoSuchsymptomException;
import com.healthmanagement.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SymptomService {

    @Autowired
    private SymptomRepository symptomRepository;

    public Symptom registerSymptom(Symptom symptom){
        return symptomRepository.save(symptom);
    }

    public Symptom getSymptomById(Long symptomId){
        Optional<Symptom> symptomById = symptomRepository.findById(symptomId);
        if(symptomById.isEmpty()){
            throw new NoSuchsymptomException("Symptom with SymptomId "+symptomId+ " is not present");
        }
        return symptomById.get();
    }

    public List<Symptom> getAllSymptoms(){
        return symptomRepository.findAll();
    }

    public String deleteSymptom(Long symptomId){
        Optional<Symptom> symptomById = symptomRepository.findById(symptomId);
        if(symptomById.isEmpty()){
            throw new NoSuchsymptomException("Symptom with SymptomId "+symptomId+ " is not present");
        }
        symptomRepository.deleteById(symptomId);
        return "Symptom with SymptomId "+symptomId+" is deleted successfully";
    }

    public Symptom updateSymptom(Symptom newSymptomDetails, Long symptomId){
        Optional<Symptom> symptomById = symptomRepository.findById(symptomId);
        if(symptomById.isEmpty()){
            throw new NoSuchsymptomException("Symptom with SymptomId "+symptomId+ " is not present");
        }
        Symptom symptom=symptomById.get();
        symptom.setSymptomDescription(newSymptomDetails.getSymptomDescription());
       // symptom.setSpecialities(newSymptomDetails.getSpecialities());
        return symptomRepository.save(symptom);
    }
}
