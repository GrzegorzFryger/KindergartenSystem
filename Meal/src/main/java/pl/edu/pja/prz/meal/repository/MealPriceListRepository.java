package pl.edu.pja.prz.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.meal.model.MealPriceList;
import pl.edu.pja.prz.meal.model.enums.MealType;

import java.util.Optional;

@Repository
public interface MealPriceListRepository extends JpaRepository<MealPriceList, Long> {
    Optional<MealPriceList> findByMealType(MealType type);
}
