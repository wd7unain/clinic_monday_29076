package com.abdalla.clinic.Service;

import com.abdalla.clinic.Entities.Doctor;
import com.abdalla.clinic.Entities.Specialization;
import com.abdalla.clinic.Repository.DoctorRepository;
import com.abdalla.clinic.Repository.OfficeRepository;
import com.abdalla.clinic.Repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepo ;
    @Autowired
    private OfficeRepository officeRepository ;
    @Autowired
    private SpecializationRepository specializationRepo ;



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
         public ResponseEntity<?> findBySpecialization (String name){
        if(name.equalsIgnoreCase("Cardiology") || name.equalsIgnoreCase("Pediatrics") ||
                 name.equalsIgnoreCase("Dermatology") ||name.equalsIgnoreCase("Neurology"))
            return new ResponseEntity<>(doctorRepo.findBySpecializations_SpecializationIgnoreCase(name),HttpStatus.OK);
        else
            return new ResponseEntity<>("invalid specialization",HttpStatus.CONFLICT);
         }

         public List<Doctor>getDoctorsWithoutOffice(){
            return doctorRepo.findByOfficeIsNull();
         }

         public ResponseEntity<?> saveSpecialization ( Long doctorId , Long specializationId){
            if(!doctorRepo.existsById(doctorId))
                return new ResponseEntity<>("The doctor with that id does not exist",HttpStatus.NOT_FOUND);

            if(!specializationRepo.existsById(specializationId))
                return new ResponseEntity<>("That specialization does not exist",HttpStatus.NOT_FOUND);

            Doctor doctor= doctorRepo.findById(doctorId).orElse(new Doctor());
            Specialization specialization=specializationRepo.findById(specializationId).orElse(new Specialization());
            Set<Specialization> specializations=doctor.getSpecializations();
            if(specializations.contains(specialization))
                return new ResponseEntity<>("The doctor already has that specialization",HttpStatus.CONFLICT);

            specializations.add(specialization);
            doctorRepo.save(doctor);
            return  new ResponseEntity<>("Specialization added to the doctor" , HttpStatus.OK);
         }

         public ResponseEntity<?> deleteDoctorSpecialization( Long doctorId , Long specializationId){
             if(!doctorRepo.existsById(doctorId))
                 return new ResponseEntity<>("The doctor with that id does not exist",HttpStatus.NOT_FOUND);

             if(!specializationRepo.existsById(specializationId))
                 return new ResponseEntity<>("That specialization does not exist",HttpStatus.NOT_FOUND);

             Doctor doctor= doctorRepo.findById(doctorId).orElse(new Doctor());
             Specialization specialization=specializationRepo.findById(specializationId).orElse(new Specialization());
             Set<Specialization> specializations=doctor.getSpecializations();

             if(!specializations.contains(specialization))
                 return new ResponseEntity<>("The doctor does not have that specialization",HttpStatus.CONFLICT);


             specializations.remove(specialization);
             doctorRepo.save(doctor);
             return new ResponseEntity<>("Specialization removed from the doctor" ,HttpStatus.OK);
         }

         public ResponseEntity<?> changeOffice (Long doctorId,int officeNumber){
             if(!doctorRepo.existsById(doctorId))
                 return new ResponseEntity<>("The doctor with that id does not exist",HttpStatus.NOT_FOUND);

             if(!officeRepository.existsByOfficeNumber(officeNumber))
                 return new ResponseEntity<>("The office with that office number does not exist",HttpStatus.NOT_FOUND);
             if(doctorRepo.existsByOffice_OfficeNumber(officeNumber))
                 return new ResponseEntity<>("That office is taken by other doctor",HttpStatus.CONFLICT);
             Doctor doctor= doctorRepo.findById(doctorId).orElse(new Doctor());
             doctor.setOffice(officeRepository.findByOfficeNumber(officeNumber));
             doctorRepo.save(doctor);

             return new ResponseEntity<>("Office changed successfully",HttpStatus.OK);


         }
    }
