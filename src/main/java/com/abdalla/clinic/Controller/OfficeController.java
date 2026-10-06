package com.abdalla.clinic.Controller;

import com.abdalla.clinic.Entities.Office;
import com.abdalla.clinic.Service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offices")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @GetMapping
    public List<Office> getAllOffices() {
        return officeService.getAllOffices();
    }

    @GetMapping("/{id}")
    public Office getOffice(@PathVariable Long id) {
        return officeService.findOffice(id);
    }

    @PostMapping
    public String addOffice(@RequestBody Office office) {
        return officeService.addOffice(office);
    }

    @PutMapping("/{id}")
    public String updateOffice(@PathVariable Long id, @RequestBody Office office) {
        return officeService.updateOffice(id, office);
    }

    @DeleteMapping("/{id}")
    public String deleteOffice(@PathVariable Long id) {
        return officeService.deleteOffice(id);
    }
}