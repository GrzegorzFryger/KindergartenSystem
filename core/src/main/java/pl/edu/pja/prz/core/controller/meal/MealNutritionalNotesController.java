package pl.edu.pja.prz.core.controller.meal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.prz.meal.facade.MealNutritionalNotesFacade;

import static pl.edu.pja.prz.core.controller.RequestMappings.API_NUTRITIONAL_NOTES;

@RestController
@RequestMapping(API_NUTRITIONAL_NOTES)
public class MealNutritionalNotesController {

    private final MealNutritionalNotesFacade mealNutritionalNotesFacade;

    public MealNutritionalNotesController(MealNutritionalNotesFacade mealNutritionalNotesFacade) {
        this.mealNutritionalNotesFacade = mealNutritionalNotesFacade;
    }
}
