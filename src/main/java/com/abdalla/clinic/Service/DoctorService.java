package com.abdalla.clinic.Service;

import com.abdalla.clinic.Entities.Doctor;
import com.abdalla.clinic.Repository.DoctorRepository;
import com.abdalla.clinic.Repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.print.Doc;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepo ;
    @Autowired
    private OfficeRepository officeRepository ;

    public String addDoctor (Doctor doctor , Long officeId) {
        if (officeId == null ){
            doctorRepo.save(doctor);
            return "saved without an office";
        }
        if (doctor.getName() != null && doctor.getName().matches("^[^0-9]*$")) {

            if (!doctorRepo.existsByOfficeId(officeId) && officeRepository.existsById(officeId)){
                doctor.setOffice(officeRepository.findById(officeId).orElse(null));
            doctorRepo.save(doctor);
            return "saved";
        } else {
            return "the office or occupied or doesn't exist";
        }
    }
            return "doctor name can't contain numbers";
        }

        public List<Doctor> getAllDoctors () {
            return doctorRepo.findAll();
        }

        public Doctor findDoctor (Long id ){

            return doctorRepo.findById(id).orElse(null);
        }

        public String updateDoctor (Long id, Doctor newDoctor){
            if (!doctorRepo.existsById(id)) {

                return "Doctor with this id not found";
            }
            Doctor existingDoctor = doctorRepo.findDoctorById(id);
            existingDoctor.setName(newDoctor.getName());
            doctorRepo.save(existingDoctor);
            return "Updated";
        }

        public String deleteDoctor (Long id){
            if (!doctorRepo.existsById(id)) {

                return "Doctor with this id not found";
            }
            Doctor doctor = doctorRepo.findDoctorById(id);
            String doctName = doctor.getName();

            doctorRepo.deleteById(id);

            return "Doctor : " + doctName + " Deleted successfully";
         }
    // (Cardiology, , Dermatology, Neurology
         public List<Doctor>findBySpecialization (String name){
        if(name.equalsIgnoreCase("Cardiology") || name.equalsIgnoreCase("Pediatrics") ||
                 name.equalsIgnoreCase("Dermatology") ||name.equalsIgnoreCase("Neurology"))
            return doctorRepo.findBySpecializations_SpecializationIgnoreCase(name);
        else
            return null;
         }

         public List<Doctor>getDoctorsWithoutOffice(){
            return doctorRepo.findByOfficeIsNull();
         }
    }
