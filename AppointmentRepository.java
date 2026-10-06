import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
    // A2: DERIVED
    List<Appointment> findByStatusOrderByAppointmentDateAsc(AppointmentStatus status);

    // A3: DERIVED
    List<Appointment> findByAppointmentDateBetweenOrderByAppointmentDateAsc(LocalDate start, LocalDate end);

    // A4: DERIVED
    boolean existsByDoctorIdAndAppointmentDateAndStatusNot(Long doctorId, LocalDate appointmentDate, AppointmentStatus status);

    // B4: JPQL (Used by PatientService)
    @Query("SELECT DISTINCT a.patient FROM Appointment a WHERE a.doctor.id = :doctorId")
    List<Patient> findPatientsByDoctorId(@Param("doctorId") Long doctorId);

    // C1: JPQL
    @Query("SELECT a.status, COUNT(a) FROM Appointment a GROUP BY a.status")
    List<Object[]> countByStatus();

    // C2: JPQL (Used by PatientService)
    @Query("SELECT a.patient FROM Appointment a GROUP BY a.patient HAVING COUNT(a) >= :min ORDER BY COUNT(a) DESC")
    List<Patient> findFrequentPatients(@Param("min") int min);

    // C4: JPQL
    @Modifying
    @Query("UPDATE Appointment a SET a.status = AppointmentStatus.CANCELLED " +
           "WHERE a.doctor.id = :doctorId AND a.appointmentDate = :date " +
           "AND a.status <> AppointmentStatus.COMPLETED")
    int cancelAppointmentsForDoctorOnDate(@Param("doctorId") Long doctorId, @Param("date") LocalDate date);

    // Bonus: JPQL
    @Modifying
    @Query("DELETE FROM Appointment a WHERE a.status = AppointmentStatus.CANCELLED AND a.appointmentDate < :date")
    int deleteCancelledBefore(@Param("date") LocalDate date);

    // Bonus: Pageable (Inherited, but explicitly declared for clarity)
    Page<Appointment> findAll(Pageable pageable);
}
