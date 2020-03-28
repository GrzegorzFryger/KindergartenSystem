package pl.edu.pja.prz.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.prz.meal.model.NutritionalNotes;

public interface MealNutritionalNotesRepository extends JpaRepository<NutritionalNotes, Long> {
}
