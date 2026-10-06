import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // B1
    public List<Doctor> findDoctorsBySpecialization(String name) {
        return doctorRepository.findBySpecializationNameIgnoreCase(name);
    }

    // B2
    public List<Doctor> findDoctorsWithoutOffice() {
        return doctorRepository.findDoctorsWithoutOffice();
    }
}
