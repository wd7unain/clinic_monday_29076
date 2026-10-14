package com.abdalla.clinic.Service;

import com.abdalla.clinic.Entities.Status;
import com.abdalla.clinic.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {
    @Autowired
    private PatientRepository patientRepo;
    @Autowired
    private DoctorRepository doctorRepo;
    @Autowired
    private OfficeRepository officeRepo;
    @Autowired
    private AppointmentRepository appointmentRepo;
    @Autowired
    private PrescriptionRepository prescriptionRepo;

    public Map<String , Long>  summary() {
    Map<String, Long> map = new LinkedHashMap<>();
        map.put("patients", patientRepo.count());
        map.put("doctors", doctorRepo.count());
        map.put("freeOffices", (long) officeRepo.freeOffices().size());   // إعادة استخدام A3
        map.put("appointmentsToday", appointmentRepo.countByDate(LocalDate.now()));
        map.put("scheduled", appointmentRepo.countByStatus(Status.SCHEDULED));
        map.put("prescriptions", prescriptionRepo.count());
        return map;
}

}
