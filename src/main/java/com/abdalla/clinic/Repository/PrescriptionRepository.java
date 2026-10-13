package com.abdalla.clinic.Repository;

import com.abdalla.clinic.Entities.Appointment;
import com.abdalla.clinic.Entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription , Long> {

    List<Prescription> findByAppointment(Appointment appointment);

    @Query("select p from Prescription p  where p.appointment.patient.ssNumber = :ssNumber order by p.issuedDate desc")
    List<Prescription> findByPatientSsNumber(@Param("ssNumber") Long ssNumber);


    boolean existsByAppointment_Id(long appointmentId);

}
