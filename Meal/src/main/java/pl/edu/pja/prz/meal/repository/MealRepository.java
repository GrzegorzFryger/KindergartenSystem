package pl.edu.pja.prz.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.meal.model.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
}
