package com.abdalla.clinic.Repository;

import com.abdalla.clinic.Entities.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

    public List<Specialization> findByDoctorsIsNull();
}
