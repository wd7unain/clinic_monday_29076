package com.abdalla.clinic.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "Appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private Date date ;
    private String reason ;
    private String status ;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // same to hid lazy initi from z resp
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "patient_id")
    private Patient patient;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // to hid e "hibernateLazyInitializer": {}, fron postman response

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
