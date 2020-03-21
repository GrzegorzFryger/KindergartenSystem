package pl.edu.pja.prz.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.prz.meal.model.MealConfiguration;

public interface MealConfigurationRepository extends JpaRepository<MealConfiguration, Long> {
}
