package com.abdalla.clinic.Repository;

import com.abdalla.clinic.Entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
    @Query("select o from Office o  left join Doctor  d on d.office.id = o.id where d.office.id is null order by o.officeNumber")
    public List<Office> freeOffices ();

    boolean existsByOfficeNumber(int officeNumber);

    Office findByOfficeNumber(int officeNumber);
}
