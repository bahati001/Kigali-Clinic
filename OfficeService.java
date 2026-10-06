import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OfficeService {
    private final OfficeRepository officeRepository;

    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    // C3
    public Optional<Object[]> getBusiestOffice() {
        List<Object[]> result = officeRepository.findBusiestOffice();
        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result.get(0));
    }
}
