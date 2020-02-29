package pl.edu.pja.prz.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.calendar.model.Absence;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
	List<Absence> findAllByChildId(UUID childId);

	List<Absence> findAllByDate(LocalDate date);
}
