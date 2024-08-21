package com.healthmanagement.service;

import com.healthmanagement.entity.Patient;
import com.healthmanagement.exception.NoSuchPatientException;
import com.healthmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient registerPatient(Patient patient){
       return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }
    public Patient getPatientById(Long patientId){
        Optional<Patient> patientById = patientRepository.findById(patientId);
        if (patientById.isEmpty()){
            throw new NoSuchPatientException("Patient with patientId "+patientId+ " is not present");
        }
        return patientById.get();
    }

    public String deletePatientById(Long patientId){
        Optional<Patient> patientById = patientRepository.findById(patientId);
        if (patientById.isEmpty()){
            throw new NoSuchPatientException("Patient with patientId "+patientId+ " is not present");
        }
        patientRepository.deleteById(patientId);
        return "Patient with patientId "+ patientId+ " is deleted successfully";
    }

    public Patient updatePatientByPatientId(Patient newPatient,Long patientId){
        Optional<Patient> patientById = patientRepository.findById(patientId);
        if (patientById.isEmpty()){
            throw new NoSuchPatientException("Patient with patientId "+patientId+ " is not present");
        }
        Patient patient = patientById.get();
        patient.setPatientName(newPatient.getPatientName());
        patient.setCity(newPatient.getCity());
        patient.setEmail(newPatient.getEmail());
        patient.setDateOfBirth(newPatient.getDateOfBirth());
        patient.setPhoneNumber(newPatient.getPhoneNumber());
        patient.setSymptoms(newPatient.getSymptoms());

        return patientRepository.save(patient);
    }
}
