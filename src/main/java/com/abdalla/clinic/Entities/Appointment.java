package com.abdalla.clinic.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "Appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private LocalDate date ;
    private String reason ;
    @Enumerated(EnumType.STRING)
    private Status status ;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // same to hid lazy initi from z resp
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "patient_id")
    private Patient patient;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // to hid e "hibernateLazyInitializer": {}, fron postman response

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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
