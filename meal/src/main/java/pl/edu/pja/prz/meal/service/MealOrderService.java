package pl.edu.pja.prz.meal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.meal.model.MealOrder;
import pl.edu.pja.prz.meal.repository.MealOrderRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class MealOrderService {

    private MealOrderRepository mealOrderRepository;

    @Autowired
    public MealOrderService(MealOrderRepository mealOrderRepository) {
        this.mealOrderRepository = mealOrderRepository;
    }

    void saveMealOrder(List<String> mealOrder) {
        mealOrder.forEach(u -> {
            mealOrderRepository.save(
                    new MealOrder(LocalDate.now(), u.split(":")[0].trim(), u.split(":")[1].trim())
            );
        });
    }

    public List<MealOrder> getAllMealOrder() {
        return mealOrderRepository.findAll();
    }

    public List<MealOrder> getAllMealOrderByDate(LocalDate orderDate) {
        return getAllMealOrderByDate(orderDate);
    }
}
