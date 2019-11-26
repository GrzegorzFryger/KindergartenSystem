package pl.edu.pja.prz.meal.service;

import pl.edu.pja.prz.meal.exception.NotFound;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;

import java.util.Optional;

public interface MealService {

    Meal createMate(MealCreateUpdateDTO meal);
    Meal getMealByID(Long id) throws NotFound;
    void deleteMealByID(Long id) throws NotFound;
    Optional<Meal> updateMeal(MealCreateUpdateDTO meal, Long mealToUpdateID) throws NotFound;
    boolean isMealPresentByID(Long id);


}
