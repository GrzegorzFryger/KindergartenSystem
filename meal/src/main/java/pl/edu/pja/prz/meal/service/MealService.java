package pl.edu.pja.prz.meal.service;

import pl.edu.pja.prz.meal.exception.MealActivityStatusException;
import pl.edu.pja.prz.meal.exception.NotFoundException;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;

import java.util.List;

public interface MealService {

    Meal createMeal(MealCreateUpdateDTO meal) throws MealActivityStatusException;
    Meal getMealByID(Long id) throws NotFoundException;
    void deleteMealByID(Long id) throws NotFoundException;
    Meal updateMeal(MealCreateUpdateDTO meal, Long mealToUpdateID) throws NotFoundException, MealActivityStatusException;
    boolean isMealPresentByID(Long id);
    List<Meal> getAllMeals();


}
