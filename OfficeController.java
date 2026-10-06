import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/offices")
public class OfficeController {
    private final OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    // C3
    @GetMapping("/busiest")
    public ResponseEntity<?> getBusiestOffice() {
        Optional<Object[]> result = officeService.getBusiestOffice();
        if (result.isEmpty()) {
            return ResponseEntity.ok("No appointments yet");
        }
        return ResponseEntity.ok(result.get());
    }
}
