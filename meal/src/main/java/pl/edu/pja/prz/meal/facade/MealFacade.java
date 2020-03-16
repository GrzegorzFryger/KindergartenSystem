package pl.edu.pja.prz.meal.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;
import pl.edu.pja.prz.meal.service.MealService;

import java.util.List;

@Service
public class MealFacade {

    private final MealService mealService;

    @Autowired
    public MealFacade(MealService mealService) {
        this.mealService = mealService;
    }

    public Meal createMeal(MealCreateUpdateDTO dto)  {
        return mealService.createMeal(dto);
    }

    public List<Meal> getAllMeals() {
        return mealService.getAllMeals();
    }

    public Meal getMealById(Long id) {
        return mealService.getMealByID(id);
    }

    public Meal updateMeal(MealCreateUpdateDTO meal, Long mealToUpdateID) {
        return mealService.updateMeal(meal, mealToUpdateID);
    }
}
