package com.abdalla.clinic.Service;

import com.abdalla.clinic.Entities.Specialization;
import com.abdalla.clinic.Repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationService {
    @Autowired
    private SpecializationRepository specializationRepo ;

    public String saveSpecialization (Specialization specialization){
        if(specialization != null){
            specializationRepo.save(specialization);
            return "saved";
        }else {
            return "Specialization can't be null";

        }

    }

    public List<Specialization> getSpecializationWithoutDoctor(){
        return specializationRepo.findByDoctorsIsNull();
    }
}
