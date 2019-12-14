package pl.edu.pja.prz.meal.service;

import pl.edu.pja.prz.meal.exception.MeaActivityStatusException;
import pl.edu.pja.prz.meal.exception.NotFound;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;

import java.util.List;

public interface MealService {

	Meal createMeal(MealCreateUpdateDTO meal) throws MeaActivityStatusException;

	Meal getMealByID(Long id) throws NotFound;

	void deleteMealByID(Long id) throws NotFound;

	Meal updateMeal(MealCreateUpdateDTO meal, Long mealToUpdateID) throws NotFound, MeaActivityStatusException;

	boolean isMealPresentByID(Long id);

	List<Meal> getAllMeals();


}
