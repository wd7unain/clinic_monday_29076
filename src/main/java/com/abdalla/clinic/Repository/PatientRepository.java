package com.abdalla.clinic.Repository;

import com.abdalla.clinic.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient , Long> {
    Patient findPatientById(long id);

    List<Patient> findByNameContainingIgnoreCase(String name);

    List<Patient> findByNameEndingWithIgnoreCase(String name);

    @Query("select distinct p from Patient p join p.appointments a where a.doctor.id = :doctorId")
    List<Patient>  findDistinctPatientByDoctorId(Long doctorId);

    @Query ("select a.patient from Appointment a group by a.patient having count(a) >= :min order by count(a) desc")
    List<Patient>  findFrequentPatient (@Param("min") long min);
}
