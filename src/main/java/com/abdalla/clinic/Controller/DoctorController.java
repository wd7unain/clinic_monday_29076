package com.abdalla.clinic.Controller;

import com.abdalla.clinic.Entities.Doctor;
import com.abdalla.clinic.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;


    @GetMapping("/getAllDocts")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping
    public String addDoctor(@RequestBody Doctor doctor , @RequestParam(required = false) Long officeId) {
        return doctorService.addDoctor(doctor , officeId);
    }

    @PutMapping("/{id}")
    public String updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        return doctorService.updateDoctor(id, doctor);
    }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        return doctorService.deleteDoctor(id);
    }

    @GetMapping("/by-specialization")
    public List<Doctor> getDoctorsBySpecialization(@RequestParam String name){
        return doctorService.findBySpecialization(name);
    }

    @GetMapping("/without-office")
    public List<Doctor> getDoctorsWithoutOffice(){
        return doctorService.getDoctorsWithoutOffice();
    }
}
