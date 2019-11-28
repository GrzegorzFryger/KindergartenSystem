package pl.edu.pja.prz.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.enums.MealStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    Optional<Meal> findMealByChildIDAndMealStatus(long id, MealStatus status);
    Optional<Meal> findMealByIdAndMealStatus(long id, MealStatus status);
    List<Meal> findAllByMealStatus(MealStatus mealStatus);
}
