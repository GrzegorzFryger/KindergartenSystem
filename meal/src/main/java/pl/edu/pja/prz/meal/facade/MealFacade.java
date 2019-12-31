package pl.edu.pja.prz.meal.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.meal.exception.MealActivityStatusException;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;
import pl.edu.pja.prz.meal.service.MealService;

@Service
public class MealFacade {

    private final MealService mealService;

    @Autowired
    public MealFacade(MealService mealService) {
        this.mealService = mealService;
    }

    public Meal createMeal(MealCreateUpdateDTO dto) throws MealActivityStatusException {
        return mealService.createMeal(dto);
    }
}
