package com.abdalla.clinic.Controller;

import com.abdalla.clinic.Entities.Doctor;
import com.abdalla.clinic.Entities.Patient;
import com.abdalla.clinic.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService ;

    @GetMapping("/by-last-name")
    public List<Patient> getPatients(@RequestParam String lastName) {
        return patientService.findPatientsByName(lastName);
    }

    @PostMapping
    public String addPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }
    @PutMapping("/{id}")
    public String updatePatient(@PathVariable Long id, @RequestBody Patient patient) {

        return patientService.updatePatient(id , patient);
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Long id) {
        return patientService.deletePatient(id);
    }

    @GetMapping ("/of-doctor/{doctorId}")
    public List<Patient> findPatientByDoctorId(@PathVariable long doctorId){
        return patientService.getPatientsByDoctorId(doctorId);
    }
}
