package com.abdalla.clinic.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id ;
    @JsonIgnore
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name ="office_id" , unique = true)
    private Office office;
    @JsonIgnore
    @OneToMany  (mappedBy = "doctor"
                ,cascade = CascadeType.ALL
                ,orphanRemoval = true )

    List<Appointment> appointments = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable ( name = "doctors_specializations" ,
            joinColumns = @JoinColumn (name = "doctor_id") ,
            inverseJoinColumns = @JoinColumn (name ="specialization_id")
            )
    private Set<Specialization> specializations= new HashSet<>();

    private  String name ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<Specialization> specializations) {
        this.specializations = specializations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
