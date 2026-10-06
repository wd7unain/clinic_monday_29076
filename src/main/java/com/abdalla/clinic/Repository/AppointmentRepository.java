package com.abdalla.clinic.Repository;

import com.abdalla.clinic.Entities.Appointment;
import com.abdalla.clinic.Entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment , Long > {
    //List<Appointment> findByStatusOrderByDateAsc(String status);

    List<Appointment> findByStatusContainingIgnoreCaseOrderByDateAsc(String status);

    List<Appointment> findByDateBetween(Date start, Date end);

    Appointment findByDateAndDoctor_Id(Date date, long doctorId);

    @Query("select a.status , COUNT(a) from Appointment a GROUP BY a.status")
    List <Object[]> countAppointmentsByStatus();
}
