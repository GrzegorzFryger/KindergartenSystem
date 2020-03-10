package pl.edu.pja.prz.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.calendar.model.DayOffWork;

@Repository
public interface DayOffWorkRepository extends JpaRepository<DayOffWork, Long> {
}
