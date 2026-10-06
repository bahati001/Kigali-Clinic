import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpecializationService {
    private final SpecializationRepository specializationRepository;

    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    // B3
    public List<Specialization> findUnusedSpecializations() {
        return specializationRepository.findUnusedSpecializations();
    }
}
