package com.abdalla.clinic.Controller;

import com.abdalla.clinic.Entities.Office;
import com.abdalla.clinic.Service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/offices")
@CrossOrigin(origins = "*")
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

    @PostMapping("/save")
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

    @GetMapping("/free")
    public List<Office> freeOffices(){
        return officeService.freeOffices();
    }
    @GetMapping("/byNumber")
    public ResponseEntity<?> byNumber(@RequestParam int officeNumber){
        Optional<Office> officeOpt=officeService.findOfficeByNumber(officeNumber);
        if (officeOpt.isEmpty())
            return new ResponseEntity<>("Office with that number is not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(officeOpt.get(), HttpStatus.OK);

    }
}