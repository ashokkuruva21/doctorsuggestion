package com.healthmanagement.service;

import com.healthmanagement.entity.Speciality;
import com.healthmanagement.exception.NoSuchSpecialityException;
import com.healthmanagement.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    public Speciality registerSpeciality(Speciality speciality){
        return specialityRepository.save(speciality);
    }

    public List<Speciality> getAllSpecialities(){
        return specialityRepository.findAll();
    }

    public Speciality getSpecialityById(Long specialityId){
        Optional<Speciality> specialityById = specialityRepository.findById(specialityId);
        if(specialityById.isEmpty()){
            throw new NoSuchSpecialityException("Speciality with specialityId "+ specialityId+ " is not present");
        }
        return specialityById.get();
    }

    public String deleteSpeciality(Long specialityId){
        Optional<Speciality> specialityById = specialityRepository.findById(specialityId);
        if(specialityById.isEmpty()){
            throw new NoSuchSpecialityException("Speciality with specialityId "+ specialityId+ " is not present");
        }
        specialityRepository.deleteById(specialityId);
        return "Speciality Details with specialityId "+specialityId+" is deleted successfully";
    }

    public Speciality updateSpeciality(Speciality newSpecialityDetails,Long specialityId){
        Optional<Speciality> specialityById = specialityRepository.findById(specialityId);
        if(specialityById.isEmpty()){
            throw new NoSuchSpecialityException("Speciality with specialityId "+ specialityId+ " is not present");
        }
        Speciality speciality=specialityById.get();
        speciality.setSpecialityName(newSpecialityDetails.getSpecialityName());
        return specialityRepository.save(speciality);
    }

}
