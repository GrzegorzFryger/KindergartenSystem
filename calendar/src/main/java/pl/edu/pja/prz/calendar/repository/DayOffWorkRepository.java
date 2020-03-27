package pl.edu.pja.prz.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.calendar.model.DayOffWork;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DayOffWorkRepository extends JpaRepository<DayOffWork, Long> {

    Optional<DayOffWork> findDayOffWorkByDate(LocalDate date);
}
