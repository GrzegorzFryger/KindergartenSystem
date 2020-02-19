package pl.edu.pja.prz.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.calendar.model.Absence;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
}
