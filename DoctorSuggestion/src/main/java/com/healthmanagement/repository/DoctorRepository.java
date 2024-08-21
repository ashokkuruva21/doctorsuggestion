package com.healthmanagement.repository;

import com.healthmanagement.entity.Doctor;
import com.healthmanagement.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query(value = "select * from doctor d where d.doctor_id IN (select ds.doctor_id from doctor_speciality ds where ds.speciality_id IN (select s.id from speciality s where s.speciality_name IN ?1))",nativeQuery = true)
    public List<Doctor> doctorsBySpeciality(List<String> specialities);
}
