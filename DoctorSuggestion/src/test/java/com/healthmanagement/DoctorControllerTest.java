package com.healthmanagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthmanagement.controller.DoctorController;
import com.healthmanagement.entity.Doctor;
import com.healthmanagement.entity.Speciality;
import com.healthmanagement.exception.NoSuchDoctorException;
import com.healthmanagement.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DoctorController.class)
class DoctorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DoctorService doctorService;

    @InjectMocks
    DoctorController doctorController;

    List<Doctor> doctors;
    Doctor doctor1,doctor2;
    @BeforeEach
    public void setUp(){

        MockitoAnnotations.openMocks(this);

        List<Speciality> specialities=new ArrayList<>();
        specialities.add(new Speciality(1L,"Cardiology"));
        specialities.add(new Speciality(2L,"Neurology"));

        doctor1 = new Doctor(1L,"Ashok","Hyderabad","ashok@gmail.com","8888888888",specialities);
        doctor2 = new Doctor(2L,"Dhanu","Banglore","dhanu@gmail.com","9123456789",specialities );

        doctors= Arrays.asList(doctor1, doctor2);
    }

    @Test
    public void getAllDoctorsTest() throws Exception {
        Mockito.when(doctorService.getAllDoctors()).thenReturn(doctors);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/doctor/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].doctorName",is("Ashok")));
    }

    @Test
    public void getDoctorById_SuccessTest() throws Exception {
        Mockito.when(doctorService.getDoctorById(1L)).thenReturn(doctor1);

        mockMvc.perform(MockMvcRequestBuilders.get("/doctor/get/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city",is("Hyderabad")));
    }

    @Test
    public void getDoctorById_ExceptionTest() throws Exception {
        Mockito.when(doctorService.getDoctorById(anyLong())).thenThrow(new NoSuchDoctorException("Doctor with the doctorId 3 is not present"));
        //doThrow(new NoSuchDoctorException("Doctor with the doctorId 3 is not present")).when(doctorService).getDoctorById(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.get("/doctor/get/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(404))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMsg").value("Doctor with the doctorId 3 is not present"));
    }

    @Test
    public  void deleteDoctor_SuccessTest() throws Exception {
        Mockito.when(doctorService.deleteDoctorById(1L)).thenReturn("Doctor with DoctorId 1 is deleted successfully");

        mockMvc.perform(MockMvcRequestBuilders.delete("/doctor/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Doctor with DoctorId 1 is deleted successfully"));
    }


    @Test
    public void DeleteDoctor_ExceptionTest() throws Exception {
        //Mockito.when(doctorService.deleteDoctorById(anyLong())).thenThrow(new NoSuchDoctorException("Doctor with the doctorId 3 is not present"));
        doThrow(new NoSuchDoctorException("Doctor with the doctorId 3 is not present")).when(doctorService).deleteDoctorById(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/doctor/delete/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(404))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMsg").value("Doctor with the doctorId 3 is not present"));
    }

    @Test
    public void createDoctorTest() throws Exception {

        ObjectMapper objectMapper=new ObjectMapper();

        Doctor newDoctor = new Doctor(null,"Raina","Hyderabad","raina@gmail.com","9999999999",Arrays.asList(new Speciality(3L,"Cardiology"),new Speciality(4L,"Neurology")));
        Doctor savedDoctor= new Doctor(1L,"Raina","Hyderabad","raina@gmail.com","9999999999",Arrays.asList(new Speciality(3L,"Cardiology"),new Speciality(4L,"Neurology")));

        Mockito.when(doctorService.registerDoctor(newDoctor)).thenReturn(savedDoctor);

        mockMvc.perform(MockMvcRequestBuilders.post("/doctor/register")
                .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newDoctor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.doctorId").value(1L))
                .andExpect(jsonPath("$.doctorName").value("Raina"))
                .andExpect(jsonPath("$.specialities[0].specialityName").value("Cardiology"));
    }


    @Test
    void updateDoctor_SuccessTest() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        Doctor updatedDoctor = new Doctor(1L,"Raina","Banglore","raina@gmail.com"
                ,"9999999999",Arrays.asList(new Speciality(3L,"Dermatology"),new Speciality(4L,"Neurology")));

        Mockito.when(doctorService.updateDoctorByDoctorId(updatedDoctor,1L)).thenReturn(updatedDoctor);

        mockMvc.perform(MockMvcRequestBuilders.put("/doctor/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDoctor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("Banglore"))
                .andExpect(jsonPath("$.specialities[0].specialityName").value("Dermatology"));
    }

    @Test
    void updateDoctor_ExceptionTest() throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();

        Doctor updatedDoctor = new Doctor(1L,"Raina","Banglore","raina@gmail.com"
                ,"9999999999",Arrays.asList(new Speciality(3L,"Dermatology"),new Speciality(4L,"Neurology")));

        //Mockito.when(doctorService.updateDoctorByDoctorId(updatedDoctor,anyLong())).thenThrow(new NoSuchDoctorException("Doctor with the doctorId 3 is not present"));
        doThrow(new NoSuchDoctorException("Doctor with the doctorId 3 is not present")).when(doctorService).updateDoctorByDoctorId(updatedDoctor,3L);

        mockMvc.perform(MockMvcRequestBuilders.put("/doctor/update/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDoctor)))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(404))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMsg").value("Doctor with the doctorId 3 is not present"));
    }



}
