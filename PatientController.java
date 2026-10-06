package kigali.clinic.rw.patient;

import kigali.clinic.rw.domain.Patient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // A1: Endpoint for finding by last name
    @GetMapping("/by-last-name")
    public ResponseEntity<List<Patient>> getByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(patientService.findPatientsByLastName(lastName));
    }

    // B4: Endpoint for patients of a doctor
    @GetMapping("/of-doctor/{doctorId}")
    public ResponseEntity<?> getPatientsOfDoctor(@PathVariable Long doctorId) {
        Optional<List<Patient>> result = patientService.findPatientsOfDoctor(doctorId);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The doctor with that id does not exist");
        }
        return ResponseEntity.ok(result.get());
    }

    // C2: Endpoint for frequent patients
    @GetMapping("/frequent")
    public ResponseEntity<List<Patient>> getFrequent(@RequestParam int min) {
        return ResponseEntity.ok(patientService.findFrequentPatients(min));
    }
}
