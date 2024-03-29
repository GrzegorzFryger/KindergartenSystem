package pl.edu.pja.prz.meal.service;

import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.NutritionalNotes;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface MealService {

    Meal createMeal(MealCreateUpdateDTO meal);
    Meal getMealByID(Long id);
    void deleteMealByID(Long id);
    Meal updateMeal(MealCreateUpdateDTO meal, Long mealToUpdateID) ;
    boolean isMealPresentByID(Long id);
    List<Meal> getAllMeals();
    Meal updateMealNutritionalNotes(Long mealId, List<NutritionalNotes> nutritionalNotes);
    boolean isNoteByIdPresentInMeal(Long nnId, Long mealId);
    List<NutritionalNotes> getNutritionalNotesByMealId(Long mealId);
    Meal markMealAsInactiveOnDemand(Long mealToMarkAsInactiveId);
    List<Meal> getAllMealsByChildId(UUID id);
}
