package com.abdalla.clinic.Service;

import com.abdalla.clinic.Entities.Appointment;
import com.abdalla.clinic.Entities.Doctor;
import com.abdalla.clinic.Entities.Patient;
import com.abdalla.clinic.Repository.AppointmentRepository;
import com.abdalla.clinic.Repository.DoctorRepository;
import com.abdalla.clinic.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String saveAppointment(Appointment app , Long doctorId , Long patientId){
        if(app == null){
            return "appointment can't be null";
        }

        Doctor doctor= doctorRepo.findById(doctorId).orElse(null);
        Patient patient = patientRepo.findById(patientId).orElse(null);


        if (doctor != null && patient != null) {
            Appointment checkApp =appointmentRepo.findByDateAndDoctor_Id(app.getDate() , doctorId);
            if(checkApp == null || checkApp.getStatus().equalsIgnoreCase("CANCELLED")){
                app.setDoctor(doctor);
                app.setPatient(patient);


                appointmentRepo.save(app);
                return "saved";
            }else {
                return "Doctor is already booked on that date";
            }

        }else {
            if(doctor == null)
                return "doctor is not exist please try again with a valid id  ";
            else
                return "patient is not exist please try again with a valid id  ";
        }
    }

    public List<Appointment> findBetween(Date start, Date end){
        return appointmentRepo.findByDateBetween(start,end);
    }
}
