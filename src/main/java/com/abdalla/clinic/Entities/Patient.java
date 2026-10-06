package com.abdalla.clinic.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Patients")
public class Patient {
    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    private  long id ;
    private  String name ;

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
}
