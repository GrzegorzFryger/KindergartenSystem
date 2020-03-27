package pl.edu.pja.prz.core.controller.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.meal.facade.MealFacade;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;

import java.util.List;

import static pl.edu.pja.prz.commons.constants.Roles.HAS_ANY_ROLE;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_MEALS;

@RestController
@RequestMapping(API_MEALS)
public class MealController {

    private final MealFacade mealFacade;

    @Autowired
    public MealController(MealFacade mealFacade) {
        this.mealFacade = mealFacade;
    }

    @PostMapping
    @PreAuthorize(HAS_ANY_ROLE)
    public Meal createMeal(@RequestBody MealCreateUpdateDTO dto) {
        return mealFacade.createMeal(dto);
    }

    @GetMapping
    @PreAuthorize(HAS_ANY_ROLE)
    public List<Meal> getAllMeals() {
        return mealFacade.getAllMeals();
    }

    @GetMapping("{id}")
    @PreAuthorize(HAS_ANY_ROLE)
    public Meal getMealByID(@PathVariable("id") Long id) {
        return mealFacade.getMealById(id);
    }

    @PutMapping("{id}")
    @PreAuthorize(HAS_ANY_ROLE)
    public Meal updateMeal(@RequestBody MealCreateUpdateDTO meal, @PathVariable("id") Long mealToUpdateID) {
        return mealFacade.updateMeal(meal,mealToUpdateID);
    }
}
