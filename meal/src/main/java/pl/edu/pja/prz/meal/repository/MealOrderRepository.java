package pl.edu.pja.prz.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.prz.meal.model.MealOrder;

import java.time.LocalDate;
import java.util.List;

public interface MealOrderRepository extends JpaRepository<MealOrder, Long> {

    List<MealOrder> findAllByOrderDate(LocalDate orderDate);
}
