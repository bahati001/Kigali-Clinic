import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // B1
    @GetMapping("/by-specialization")
    public ResponseEntity<List<Doctor>> getBySpecialization(@RequestParam String name) {
        return ResponseEntity.ok(doctorService.findDoctorsBySpecialization(name));
    }

    // B2
    @GetMapping("/without-office")
    public ResponseEntity<List<Doctor>> getWithoutOffice() {
        return ResponseEntity.ok(doctorService.findDoctorsWithoutOffice());
    }
}
