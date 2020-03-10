package pl.edu.pja.prz.meal.service;

import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;

import java.util.List;

public interface MealService {

    Meal createMeal(MealCreateUpdateDTO meal);
    Meal getMealByID(Long id);
    void deleteMealByID(Long id);
    Meal updateMeal(MealCreateUpdateDTO meal, Long mealToUpdateID) ;
    boolean isMealPresentByID(Long id);
    List<Meal> getAllMeals();


}
