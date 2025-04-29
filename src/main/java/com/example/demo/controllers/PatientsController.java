package com.example.demo.controllers;
import com.example.demo.models.Patients;
import com.example.demo.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/Patients")
public class PatientsController {
    @Autowired
    PatientsRepository patientsRepository;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Patients> getAll() {
        return patientsRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patients> getPatientById(@PathVariable int id) {
        return patientsRepository.findById(String.valueOf(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/dob")
    public List<Patients> getPatientsByDateOfBirthRange(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return patientsRepository.findByDateOfBirthBetween(from, to);
    }

    @GetMapping("/by-department")
    public List<Patients> getByDepartment(@RequestParam String department) {
        return patientsRepository.findByEmployeeDepartment(department);
    }

    @GetMapping("/by-status-off")
    public List<Patients> getByEmployeeStatusOff() {
        return patientsRepository.findByEmployeeStatusOff();
    }

}
