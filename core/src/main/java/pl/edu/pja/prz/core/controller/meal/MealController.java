package pl.edu.pja.prz.core.controller.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.meal.facade.MealFacade;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;

import java.util.List;

@RestController
@RequestMapping("api/meal")
//TODO: ADD @PreAuthorize annotation with proper roles from Roles.java class
public class MealController {

    private final MealFacade mealFacade;

    @Autowired
    public MealController(MealFacade mealFacade) {
        this.mealFacade = mealFacade;
    }

    @PostMapping
    public Meal createMeal(@RequestBody MealCreateUpdateDTO dto) {
        return mealFacade.createMeal(dto);
    }

    @GetMapping
    public List<Meal> getAllMeals() {
        return mealFacade.getAllMeals();
    }

    @GetMapping
    public Meal getMealByID(@RequestParam("id") Long id) {
        return mealFacade.getMealById(id);
    }
}
