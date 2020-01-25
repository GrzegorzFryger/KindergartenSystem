package pl.edu.pja.prz.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.meal.model.MealPrice;
import pl.edu.pja.prz.meal.model.enums.MealType;

import java.util.Optional;

@Repository
public interface MealPriceRepository extends JpaRepository<MealPrice, Long> {
	Optional<MealPrice> findByMealType(MealType type);

	@Query(value = "SELECT m.mealPrice from MealPrice m where m.mealType=?1")
	Double findMealPriceByMealType(MealType mealType);
}
