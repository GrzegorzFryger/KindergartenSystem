package pl.edu.pja.prz.core.controller.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.prz.meal.exception.MealActivityStatusException;
import pl.edu.pja.prz.meal.facade.MealFacade;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;

@RestController
@RequestMapping("api/meal")
public class MealController {

    private final MealFacade mealFacade;

    @Autowired
    public MealController(MealFacade mealFacade) {
        this.mealFacade = mealFacade;
    }

    @PostMapping
    public Meal createMale(@RequestBody MealCreateUpdateDTO dto) throws MealActivityStatusException {
        return mealFacade.createMeal(dto);
    }
}
