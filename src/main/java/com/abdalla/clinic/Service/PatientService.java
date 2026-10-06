package com.abdalla.clinic.Service;

import com.abdalla.clinic.Entities.Patient;
import com.abdalla.clinic.Repository.DoctorRepository;
import com.abdalla.clinic.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepo ;

    @Autowired
    private DoctorRepository doctorRepo ;

    public String addPatient ( Patient patient){                         //
        if(patient.getName() != null && patient.getName().matches("^[^0-9]*$")  ){
        patientRepo.save(patient);
            return "saved";
        }
        return "Patient name can't be null or contain numbers";
    }
    public Patient findPatient(Long id){

        return patientRepo.findById(id).orElse(null);
    }

    public List<Patient> findPatientsByName(String name){
       return patientRepo.findByNameEndingWithIgnoreCase(name);
    }

    public String updatePatient(Long id , Patient newPatient){
        if(!patientRepo.existsById(id)){
            return "patient not found";
        }
        Patient existingPatient = patientRepo.findPatientById(id);
        existingPatient.setName(newPatient.getName());
        patientRepo.save(existingPatient);
        return "updated";
    }

    public String deletePatient(Long id){
        if(!patientRepo.existsById(id)){
            return "patient not found";
        }
        Patient patient = patientRepo.findPatientById(id);

        String patName =patient.getName();

        patientRepo.deleteById(id);

        return "Patient : "+patName+" Deleted successfully";
    }

    public List<Patient> getPatientsByDoctorId(Long doctorId){
        if(doctorRepo.existsById(doctorId))
            return patientRepo.findDistinctPatientByDoctorId(doctorId);
            else
            return new ArrayList<>()   ;

    }
}
