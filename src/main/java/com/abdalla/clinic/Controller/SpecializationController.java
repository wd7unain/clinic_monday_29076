package com.abdalla.clinic.Controller;

import com.abdalla.clinic.Entities.Specialization;
import com.abdalla.clinic.Service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/specializations")
public class SpecializationController {
    @Autowired
    private SpecializationService specializationService ;
    @PostMapping
    public String addSpecialization(@RequestBody Specialization specialization){
        return specializationService.saveSpecialization(specialization);
    }
    @GetMapping("/unused")
    public List<Specialization> getSpecializationWithoutDoctor(){
        return specializationService.getSpecializationWithoutDoctor();
    }
}
