package com.abdalla.clinic.Service;

import com.abdalla.clinic.Entities.Appointment;
import com.abdalla.clinic.Entities.Doctor;
import com.abdalla.clinic.Entities.Office;
import com.abdalla.clinic.Entities.Patient;
import com.abdalla.clinic.Repository.AppointmentRepository;
import com.abdalla.clinic.Repository.DoctorRepository;
import com.abdalla.clinic.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepo ;

    @Autowired
    DoctorRepository doctorRepo ;

    @Autowired
    PatientRepository patientRepo ;

    public List<Appointment> findAppByStatus(String status){
        return appointmentRepo.findByStatusContainingIgnoreCaseOrderByDateAsc(status);
    }

    public ResponseEntity<?> saveAppointment(Appointment app , Long doctorId , Long patientId){
        if(app == null){
            return new ResponseEntity<>("appointment can't be null" , HttpStatus.BAD_REQUEST);
        }

        Doctor doctor= doctorRepo.findById(doctorId).orElse(null);
        Patient patient = patientRepo.findById(patientId).orElse(null);

        LocalDate appDate =app.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if(appDate.isBefore(LocalDate.now()))
            return new ResponseEntity<>( "Appointment date cannot be in the past" ,HttpStatus.BAD_REQUEST);

        if (doctor != null && patient != null) {
            Appointment checkApp =appointmentRepo.findByDateAndDoctor_Id(app.getDate() , doctorId);
            if(checkApp == null || checkApp.getStatus().equalsIgnoreCase("CANCELLED")){
                app.setDoctor(doctor);
                app.setPatient(patient);
                app.setStatus("SCHEDULED");


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

    public List<Appointment> findBetween(Date start, Date end){
        return appointmentRepo.findByDateBetween(start,end);
    }

    public List<Object[]> countAppointmentsByStatus(){
        return appointmentRepo.countAppointmentsByStatus();
    }
}
