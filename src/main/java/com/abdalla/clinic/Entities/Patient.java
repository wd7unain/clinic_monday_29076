package com.abdalla.clinic.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Patients")
public class Patient {
    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    private  long id ;
    private Long ssNumber;
    private  String name ;

    @JsonIgnore
    @OneToMany (mappedBy = "patient" , cascade = CascadeType.ALL , orphanRemoval = true )
    private List<Appointment> appointments = new ArrayList<>() ;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSsNumber() {
        return ssNumber;
    }

    public void setSsNumber(Long ssNumber) {
        this.ssNumber = ssNumber;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
