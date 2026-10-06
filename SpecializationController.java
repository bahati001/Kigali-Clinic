import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/specializations")
public class SpecializationController {
    private final SpecializationService specializationService;

    public SpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    // B3
    @GetMapping("/unused")
    public ResponseEntity<List<Specialization>> getUnused() {
        return ResponseEntity.ok(specializationService.findUnusedSpecializations());
    }
}
