package com.example.demo.repositories;

import com.example.demo.models.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, String> {
    List<Patients> findByDateOfBirthBetween(LocalDate start, LocalDate end);

    @Query("SELECT p FROM Patients p WHERE p.admittedBy.department = :department")
    List<Patients> findByEmployeeDepartment(@Param("department") String department);

    @Query("SELECT p FROM Patients p WHERE p.admittedBy.status = 'OFF'")
    List<Patients> findByEmployeeStatusOff();
}
