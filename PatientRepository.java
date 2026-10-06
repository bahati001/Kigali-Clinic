package kigali.clinic.rw.patient;

import kigali.clinic.rw.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    
    // A1: DERIVED - Find patients by last name (IgnoreCase, OrderBy)
    List<Patient> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
    
}
