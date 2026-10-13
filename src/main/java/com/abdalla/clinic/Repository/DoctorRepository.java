package com.abdalla.clinic.Repository;

import com.abdalla.clinic.Entities.Doctor;
import com.abdalla.clinic.Entities.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findDoctorById(long id);

    Doctor findDoctorByName(String name);

    boolean existsByOfficeId(Long id);

    @Query ("select d from  Doctor d join  d.specializations s where LOWER(s.specialization) = LOWER(:name) ")
    List<Doctor> findBySpecializations_SpecializationIgnoreCase(String name);

    
    public List<Doctor> findByOfficeIsNull();

    boolean existsByOffice_OfficeNumber(int officeOfficeNumber);

}
