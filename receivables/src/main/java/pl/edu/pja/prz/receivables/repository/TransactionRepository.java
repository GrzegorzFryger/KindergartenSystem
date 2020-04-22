package pl.edu.pja.prz.receivables.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.receivables.model.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByChildId(UUID childId);

    List<Transaction> findAllByGuardianId(UUID guardianId);

    List<Transaction> findAllByGuardianIdIsNullOrChildIdIsNull();

    @Query("select t from Transaction t where t.guardianId = :guardianId " +
            "and t.transactionDate >= :start " +
            "and t.transactionDate <= :end")
    List<Transaction> findAllByGuardianIdBetweenDates(@Param("guardianId") UUID guardianId,
                                                      @Param("start") LocalDate start,
                                                      @Param("end") LocalDate end);

    @Query("select t from Transaction t where t.childId = :childId " +
            "and t.transactionDate >= :start " +
            "and t.transactionDate <= :end")
    List<Transaction> findAllByChildIdBetweenDates(@Param("childId") UUID childId,
                                                   @Param("start") LocalDate start,
                                                   @Param("end") LocalDate end);

    @Query("select t from Transaction t where t.transactionDate >= :start " +
            "and t.transactionDate <= :end")
    List<Transaction> findAllTransactionsForPastMonth(@Param("start") LocalDate start,
                                                      @Param("end") LocalDate end);
}
