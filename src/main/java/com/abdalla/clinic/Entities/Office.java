package com.abdalla.clinic.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Offices")
public class Office {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id ;
    private int officeNumber ;
    private int floor ;

    @OneToOne (mappedBy = "office")
    private Doctor doctor ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(int officeNumber) {
        this.officeNumber = officeNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
