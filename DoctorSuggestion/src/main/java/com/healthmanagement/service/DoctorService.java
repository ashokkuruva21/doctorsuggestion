package com.healthmanagement.service;

import com.healthmanagement.entity.Doctor;
import com.healthmanagement.exception.NoSuchDoctorException;
import com.healthmanagement.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    public Doctor registerDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long doctorId){
        Optional<Doctor> doctorById = doctorRepository.findById(doctorId);
        if(doctorById.isEmpty()){
            throw new NoSuchDoctorException("Doctor with the doctorId "+ doctorId +" is not present");
        }
        else{
            return doctorById.get();
        }
    }

    public String DeleteDoctorById(Long doctorId){
        Optional<Doctor> doctorById = doctorRepository.findById(doctorId);
        if(doctorById.isPresent()){
            doctorRepository.deleteById(doctorId);
            return "Doctor with DoctorId "+ doctorId +" is deleted successfully";
        }
        else {
            throw new NoSuchDoctorException("Doctor with the doctorId "+ doctorId +" is not present");
        }
    }

    public Doctor updateDoctorByDoctorId(Doctor newDoctorDetails, Long doctorId){
        Optional<Doctor> doctorById = doctorRepository.findById(doctorId);
        if (doctorById.isEmpty()){
            throw new NoSuchDoctorException("Doctor with the doctorId "+ doctorId +" is not present");
        }
        else{
            Doctor doctor = doctorById.get();
            doctor.setDoctorName(newDoctorDetails.getDoctorName());
            doctor.setEmail(newDoctorDetails.getEmail());
            doctor.setCity(newDoctorDetails.getCity());
            doctor.setPhoneNumber(newDoctorDetails.getPhoneNumber());
            doctor.setSpecialities(newDoctorDetails.getSpecialities());

            return doctorRepository.save(doctor);
        }
    }

}
