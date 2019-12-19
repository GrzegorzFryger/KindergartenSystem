package pl.edu.pja.prz.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.enums.MealStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
	Optional<Meal> findMealByChildIDAndMealStatus(UUID id, MealStatus status);
	Optional<Meal> findMealByIdAndMealStatus(Long id, MealStatus status);
	List<Meal> findAllByMealStatus(MealStatus mealStatus);
}
