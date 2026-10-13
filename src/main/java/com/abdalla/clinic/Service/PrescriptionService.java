package com.abdalla.clinic.Service;

import com.abdalla.clinic.Entities.Appointment;
import com.abdalla.clinic.Entities.Prescription;
import com.abdalla.clinic.Entities.Status;
import com.abdalla.clinic.Repository.AppointmentRepository;
import com.abdalla.clinic.Repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepo ;
    @Autowired
    private AppointmentRepository appointmentRepo;

    public ResponseEntity<?> save(Prescription prescription, Long appId){
        Appointment app = appointmentRepo.findById(appId).orElse(null);
        if (app == null)
            return new ResponseEntity<>("The appointment with that id does not exist", HttpStatus.NOT_FOUND);


        if (app.getStatus() != Status.COMPLETED)
            return new ResponseEntity<>("Prescriptions are only for COMPLETED appointments", HttpStatus.BAD_REQUEST);

        prescription.setId(null);
        prescription.setIssuedDate(LocalDate.now());
        prescription.setAppointment(app);

        prescriptionRepo.save(prescription);

            return new ResponseEntity<>("Prescription saved successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<?> findByAppointment(Long appointmentId) {
        Appointment app = appointmentRepo.findById(appointmentId).orElse(null);
        if(app ==null)
            return new ResponseEntity<>("The appointment with that id does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(prescriptionRepo.findByAppointment(app), HttpStatus.OK);
    }

    public List<Prescription> findByPatient(Long ssNumber) {
        return prescriptionRepo.findByPatientSsNumber(ssNumber);
    }

    public ResponseEntity<?> update(Long id, Prescription newData) {
        Prescription p = prescriptionRepo.findById(id).orElse(null);
        if (p == null)
            return new ResponseEntity<>("The prescription with that id does not exist", HttpStatus.NOT_FOUND);

        p.setMedicineName(newData.getMedicineName());
        p.setDosage(newData.getDosage());
        p.setDurationDays(newData.getDurationDays());


        prescriptionRepo.save(p);

        return new ResponseEntity<>("Prescription updated successfully", HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Long id) {
        if (!prescriptionRepo.existsById(id))
            return new ResponseEntity<>("The prescription with that id does not exist", HttpStatus.NOT_FOUND);

        prescriptionRepo.deleteById(id);


        return new ResponseEntity<>("Prescription deleted successfully", HttpStatus.OK);
        //retu
    }
}
