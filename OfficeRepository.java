import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
    // C3: JPQL
    @Query("SELECT o.name, o.number, COUNT(a) FROM Appointment a JOIN a.doctor d JOIN d.office o " +
           "GROUP BY o.id, o.name, o.number ORDER BY COUNT(a) DESC")
    List<Object[]> findBusiestOffice();
}
