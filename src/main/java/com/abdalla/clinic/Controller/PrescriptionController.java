package com.abdalla.clinic.Controller;

import com.abdalla.clinic.Entities.Prescription;
import com.abdalla.clinic.Service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService ;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Prescription prescription, @RequestParam Long appointmentId) {
        return prescriptionService.save(prescription, appointmentId);
    }

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<?>findByAppointment(@PathVariable Long appointmentId) {
        return prescriptionService.findByAppointment(appointmentId);
    }

    @GetMapping("/patient/{ssNumber}")
    public List<Prescription> findByPatient(@PathVariable Long ssNumber) {
        return prescriptionService.findByPatient(ssNumber);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Prescription prescription) {
        return prescriptionService.update(id, prescription);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return prescriptionService.delete(id);
    }
}
