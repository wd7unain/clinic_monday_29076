package com.abdalla.clinic.Controller;

import com.abdalla.clinic.Entities.Doctor;
import com.abdalla.clinic.Repository.DoctorRepository;
import com.abdalla.clinic.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DoctorRepository doctorRepo;



    @GetMapping("/getAllDocts")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping("/save")
    public String addDoctor(@RequestBody Doctor doctor , @RequestParam(required = false) Long officeId) {
        return doctorService.addDoctor(doctor , officeId);
    }

    @GetMapping("/{doctId}")
    public Doctor getDoctorById(@PathVariable Long doctId){
        return doctorRepo.findDoctorById(doctId);
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
    public ResponseEntity<?> getDoctorsBySpecialization(@RequestParam String name){
        return doctorService.findBySpecialization(name);
    }

    @GetMapping("/without-office")
    public List<Doctor> getDoctorsWithoutOffice(){
        return doctorService.getDoctorsWithoutOffice();
    }

    @PutMapping("/{doctorId}/specializations/{specializationId}")
    public ResponseEntity<?> saveSpecialization(@PathVariable  Long doctorId ,@PathVariable Long specializationId){
        return doctorService.saveSpecialization(doctorId,specializationId);
    }

    @DeleteMapping("/{doctorId}/specializations/{specializationId}")
    public ResponseEntity<?> deleteDoctorSpecialization(@PathVariable  Long doctorId ,@PathVariable Long specializationId){
        return  doctorService.deleteDoctorSpecialization(doctorId , specializationId);
    }

    @PatchMapping("/{doctorId}/office")
    public ResponseEntity<?> changeOffice(@PathVariable Long doctorId , @RequestParam int officeNumber){
        return doctorService.changeOffice(doctorId,officeNumber);
    }

    @GetMapping("/{doctorId}/agenda")
    public ResponseEntity<?> agenda(@PathVariable Long doctorId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        return doctorService.agenda(doctorId, date);
    }

    @GetMapping("/search")
    public List<Doctor> searchByName(@RequestParam String name){
        return doctorService.searchByName(name);
    }

}
