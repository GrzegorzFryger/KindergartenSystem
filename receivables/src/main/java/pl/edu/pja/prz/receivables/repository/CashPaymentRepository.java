package pl.edu.pja.prz.receivables.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.edu.pja.prz.receivables.model.CashPayment;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CashPaymentRepository extends JpaRepository<CashPayment, Long> {
    List<CashPayment> findAllByGuardianId(UUID guardianId);

    List<CashPayment> findAllByChildId(UUID childId);

    @Query("select c from CashPayment c where c.guardianId = :guardianId " +
            "and c.transactionDate >= :start " +
            "and c.transactionDate <= :end")
    List<CashPayment> findAllByGuardianIdBetweenDates(@Param("guardianId") UUID guardianId,
                                                      @Param("start") LocalDate start,
                                                      @Param("end") LocalDate end);

    @Query("select c from CashPayment c where c.childId = :childId " +
            "and c.transactionDate >= :start " +
            "and c.transactionDate <= :end")
    List<CashPayment> findAllByChildIdBetweenDates(@Param("childId") UUID childId,
                                                   @Param("start") LocalDate start,
                                                   @Param("end") LocalDate end);
}
