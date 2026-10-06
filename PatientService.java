package kigali.clinic.rw.patient;

import kigali.clinic.rw.domain.Patient;
import kigali.clinic.rw.repository.AppointmentRepository; // Adjust import if needed
import kigali.clinic.rw.repository.DoctorRepository;     // Adjust import if needed
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    public PatientService(PatientRepository patientRepository, 
                          AppointmentRepository appointmentRepository, 
                          DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
    }

    // A1: Service for finding by last name
    public List<Patient> findPatientsByLastName(String lastName) {
        return patientRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    // B4: Service for finding patients of a specific doctor
    public Optional<List<Patient>> findPatientsOfDoctor(Long doctorId) {
        if (!doctorRepository.existsById(doctorId)) {
            return Optional.empty(); // Signals 404 to the controller
        }
        return Optional.of(appointmentRepository.findPatientsByDoctorId(doctorId));
    }

    // C2: Service for finding frequent patients
    public List<Patient> findFrequentPatients(int min) {
        return appointmentRepository.findFrequentPatients(min);
    }
}
