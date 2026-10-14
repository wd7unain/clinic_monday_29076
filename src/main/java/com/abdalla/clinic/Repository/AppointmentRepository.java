package com.abdalla.clinic.Repository;

import com.abdalla.clinic.Entities.Appointment;
import com.abdalla.clinic.Entities.Office;
import com.abdalla.clinic.Entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment , Long > {
    //List<Appointment> findByStatusOrderByDateAsc(String status);

    List<Appointment> findByStatus(Status status);

    List<Appointment> findByDateBetween(LocalDate start, LocalDate end);

    Appointment findByDateAndDoctor_Id(LocalDate date, long doctorId);

    @Query("select a.status , COUNT(a) from Appointment a GROUP BY a.status")
    List <Object[]> countAppointmentsByStatus();

    boolean existsByDoctor_IdAndDateAndStatusNot(Long attr0, LocalDate date, Status status);

    List<Appointment> findByDoctor_IdAndDateAndStatusNot(Long doctorId, LocalDate date, Status status);


    List<Appointment> findByPatient_SsNumberAndDateGreaterThanEqualAndStatusInOrderByDateAsc(Long ssNumber, LocalDate date, List<Status> statuses);

    Long countByDate(LocalDate date);

    Long countByStatus(Status status);
}
