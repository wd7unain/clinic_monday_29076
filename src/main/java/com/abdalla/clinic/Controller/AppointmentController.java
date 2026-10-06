package com.abdalla.clinic.Controller;

import com.abdalla.clinic.Entities.Appointment;
import com.abdalla.clinic.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appService ;
    @PostMapping
    public String saveAppointment(@RequestBody Appointment app , @RequestParam Long doctorId , @RequestParam Long patientId){
        return appService.saveAppointment(app ,doctorId ,patientId) ;

    }
    @GetMapping("/by-status")
    public List<Appointment> findByStatus(@RequestParam String status){
        return appService.findAppByStatus(status);
    }

    @GetMapping("/between")
    public List<Appointment> findBetween(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end){
        return appService.findBetween(start , end) ;
    }
    @GetMapping("/stats/by-status")
    public List<Object[]> countAppointmentsByStatus(){
        return appService.countAppointmentsByStatus();
    }
}
