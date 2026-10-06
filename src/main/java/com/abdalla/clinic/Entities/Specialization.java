package com.abdalla.clinic.Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

//specialization
@Entity
@Table (name ="Specialisations")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String specialization ;

    @ManyToMany (mappedBy = "specializations")
    private Set<Doctor> doctors =new HashSet<>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
