package com.healthmanagement.service;

import com.healthmanagement.entity.Doctor;
import com.healthmanagement.entity.Patient;
import com.healthmanagement.entity.Speciality;
import com.healthmanagement.entity.Symptom;
import com.healthmanagement.exception.DoctorUnavailableException;
import com.healthmanagement.exception.NoSuchPatientException;
import com.healthmanagement.repository.DoctorRepository;
import com.healthmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class SuggestDoctorService {

    @Autowired
    private PatientRepository patientRepository;

    private String[][] specialistForSymptom = {{"Chest Pain","Cardiology"},{"Chest Pain","Pulmonology"},
            {"Headache","Neurology"},{"Headache","Psychiatry"},
            {"Rash","Dermatology"},{"Joint Pain","Orthopedics"},
            {"Fever","Pediatrics"},{"Anxiety","Psychiatry"},{"Abdominal Pain","Gastroenterology"},{"Blurred Vision","Ophthalmology"},
            {"Shortness of Breath","Pulmonology"},{"Fatigue","Endocrinology"}};

    @Autowired
    private DoctorRepository doctorRepository;
    public List<Doctor> suggestDoctor(Long patientId){
        Optional<Patient> patientById = patientRepository.findById(patientId);
        if(patientById.isEmpty()){
            throw new NoSuchPatientException("Patient with patientId "+ patientId+" is not present");
        }
        Patient patient=patientById.get();
        String city=patient.getCity();
        List<Symptom> symptoms=patient.getSymptoms();
        return this.doctorsBasedOnSymptoms(symptoms);

    }

    public List<Doctor> doctorsBasedOnSymptoms(List<Symptom> symptoms){
        List<String> specialities = new ArrayList<>();
        List<Doctor> doctorsList = null;
        for(Symptom symptom:symptoms){

            for(int i=0;i<specialistForSymptom.length;i++){
                if(symptom.getSymptomDescription().equals(specialistForSymptom[i][0]) ){
                    specialities.add(specialistForSymptom[i][1]);
                }
            }
        }
        doctorsList = doctorRepository.doctorsBySpeciality(specialities);
        if(doctorsList.isEmpty()){
            throw new DoctorUnavailableException("Doctor is not available for these symptom");
        }
        return doctorsList;
    }

    public List<Doctor> doctorsBasedOnSymptomsAndCity(Long patientId){
        Optional<Patient> patientById = patientRepository.findById(patientId);
        if(patientById.isEmpty()){
            throw new NoSuchPatientException("Patient with patientId "+ patientId+" is not present");
        }
        Patient patient=patientById.get();
        String city=patient.getCity();
        List<Symptom> symptoms=patient.getSymptoms();
        List<String> specialities = new ArrayList<>();
        List<Doctor> doctorsList = null;
        for(Symptom symptom:symptoms){

            for(int i=0;i<specialistForSymptom.length;i++){
                if(symptom.getSymptomDescription().equals(specialistForSymptom[i][0]) ){
                    specialities.add(specialistForSymptom[i][1]);
                }
            }
        }
        doctorsList = doctorRepository.doctorsBySpeciality(specialities);

        List<Doctor> doctorListBasedOnCityAndSymptoms=new ArrayList<>();
        for(Doctor doctor:doctorsList){
            if(city.equals(doctor.getCity())){
                doctorListBasedOnCityAndSymptoms.add(doctor);
            }
        }
        if(doctorListBasedOnCityAndSymptoms.isEmpty()){
            throw new DoctorUnavailableException("Doctor is not available for the symptoms of the patient had");
        }
        return doctorListBasedOnCityAndSymptoms;
    }
}
