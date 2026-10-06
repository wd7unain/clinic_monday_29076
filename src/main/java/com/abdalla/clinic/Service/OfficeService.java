package com.abdalla.clinic.Service;

import com.abdalla.clinic.Entities.Office;
import com.abdalla.clinic.Repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {

    @Autowired
    private OfficeRepository officeRepo;

    public String addOffice(Office office) {
        officeRepo.save(office);
        return "saved";
    }

    public List<Office> getAllOffices() {
        return officeRepo.findAll();
    }

    public Office findOffice(Long id) {
        return officeRepo.findById(id).orElse(null);
    }

    public String updateOffice(Long id, Office newOffice) {
        if (!officeRepo.existsById(id)) {
            return "Office not found";
        }
        Office existingOffice = officeRepo.findById(id).get();
        existingOffice.setOfficeNumber(newOffice.getOfficeNumber());
        existingOffice.setFloor(newOffice.getFloor());
        officeRepo.save(existingOffice);
        return "updated";
    }

    public String deleteOffice(Long id) {
        if (!officeRepo.existsById(id)) {
            return "Office not found";
        }
        Office office = officeRepo.findById(id).get();
        int officeNum = office.getOfficeNumber();
        officeRepo.deleteById(id);
        return "Office " + officeNum + " Deleted successfully";
    }
}