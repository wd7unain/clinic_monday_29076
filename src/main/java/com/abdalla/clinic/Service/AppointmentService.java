package com.abdalla.clinic.Service;

import com.abdalla.clinic.Entities.*;
import com.abdalla.clinic.Repository.AppointmentRepository;
import com.abdalla.clinic.Repository.DoctorRepository;
import com.abdalla.clinic.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepo ;

    @Autowired
    DoctorRepository doctorRepo ;

    @Autowired
    PatientRepository patientRepo ;

    public List<Appointment> findByStatus(Status status){
        return appointmentRepo.findByStatus(status);
    }

    public ResponseEntity<?> saveAppointment(Appointment app , Long doctorId , Long patientId){
        if(app == null){
            return new ResponseEntity<>("appointment can't be null" , HttpStatus.BAD_REQUEST);
        }

        Doctor doctor= doctorRepo.findById(doctorId).orElse(null);
        Patient patient = patientRepo.findById(patientId).orElse(null);

        if(app.getDate().isBefore(LocalDate.now()))
            return new ResponseEntity<>( "Appointment date cannot be in the past" ,HttpStatus.BAD_REQUEST);

        if (doctor != null && patient != null) {
            if ( appointmentRepo.existsByDoctor_IdAndDateAndStatusNot(doctorId , app.getDate(), Status.CANCELLED)){
                app.setDoctor(doctor);
                app.setPatient(patient);
                app.setStatus(Status.SCHEDULED);


                appointmentRepo.save(app);
                return new ResponseEntity<>( "saved" , HttpStatus.OK);
            }else {
                return new ResponseEntity<>( "Doctor is already booked on that date" , HttpStatus.CONFLICT);
            }

        }else {
            if(doctor == null)
                return new ResponseEntity<>( "doctor is not exist please try again with a valid id  " , HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>( "patient is not exist please try again with a valid id  ", HttpStatus.NOT_FOUND);
        }
    }

    public List<Appointment> findBetween(LocalDate start, LocalDate end){
        return appointmentRepo.findByDateBetween(start,end);
    }

    public List<Object[]> countAppointmentsByStatus(){
        return appointmentRepo.countAppointmentsByStatus();
    }

    public ResponseEntity<?> changeStatus(Long appId , Status newStatus){
        Appointment app = appointmentRepo.findById(appId).orElse(null);
        if(app==null)
            return new ResponseEntity<>("The appointment with that id does not exist", HttpStatus.NOT_FOUND);
        Status currentStatus = app.getStatus();

        if(currentStatus == Status.SCHEDULED &&(newStatus ==Status.CONFIRMED ||newStatus ==Status.CANCELLED )
        ||currentStatus == Status.CONFIRMED &&(newStatus ==Status.COMPLETED ||newStatus ==Status.CANCELLED) ){
            app.setStatus(newStatus);
            appointmentRepo.save(app);
            return new ResponseEntity<>("Appointment status updated successfully", HttpStatus.OK);

        }else {
            return new ResponseEntity<>("Cannot change status from " + currentStatus + " to " + newStatus,
                    HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> reschedule(Long id, LocalDate newDate) {
        Appointment app = appointmentRepo.findById(id).orElse(null);
        if (app == null)
            return new ResponseEntity<>("The appointment with that id does not exist", HttpStatus.NOT_FOUND);

        if (app.getStatus() == Status.COMPLETED || app.getStatus() == Status.CANCELLED)
            return new ResponseEntity<>("A " + app.getStatus() + " appointment cannot be rescheduled",
                    HttpStatus.BAD_REQUEST);

        if (newDate.isBefore(LocalDate.now()))
            return new ResponseEntity<>("Appointment date cannot be in the past", HttpStatus.BAD_REQUEST);

        if (appointmentRepo.existsByDoctor_IdAndDateAndStatusNot(
                app.getDoctor().getId(), newDate, Status.CANCELLED))
            return new ResponseEntity<>("Doctor is already booked on that date", HttpStatus.CONFLICT);

        app.setDate(newDate);
        app.setStatus(Status.SCHEDULED);
        appointmentRepo.save(app);
        return new ResponseEntity<>("Appointment rescheduled successfully", HttpStatus.OK);
    }
}
