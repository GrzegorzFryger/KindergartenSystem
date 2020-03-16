package pl.edu.pja.prz.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.calendar.model.Absence;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
	List<Absence> findAllByChildId(UUID childId);

	List<Absence> findAllByDate(LocalDate date);

	@Query("select c from Absence c where c.childId = :childId " +
			"and c.date >= :start " +
			"and c.date <= :end")
	List<Absence> findAllByChildIdBetweenDates(@Param("childId") UUID childId,
												   @Param("start") LocalDate start,
												   @Param("end") LocalDate end);
}
