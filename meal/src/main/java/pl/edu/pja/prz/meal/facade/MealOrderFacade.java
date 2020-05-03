package pl.edu.pja.prz.meal.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.meal.model.MealOrder;
import pl.edu.pja.prz.meal.service.MealOrderService;

import java.time.LocalDate;
import java.util.List;

@Service
public class MealOrderFacade {

    private final MealOrderService mealOrderService;

    @Autowired
    public MealOrderFacade(MealOrderService mealOrderService) {
        this.mealOrderService = mealOrderService;
    }



    public List<MealOrder> getAllMealOrder() {
        return mealOrderService.getAllMealOrder();
    }

    public List<MealOrder> getAllMealOrderByDate(LocalDate orderDate) {
        return mealOrderService.getAllMealOrderByDate(orderDate);
    }


}
