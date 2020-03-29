package pl.edu.pja.prz.meal.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.meal.service.MealNutritionalNotesService;

@Service
public class MealNutritionalNotesFacade {

    private final MealNutritionalNotesService mealNutritionalNotesService;

    @Autowired
    public MealNutritionalNotesFacade(MealNutritionalNotesService mealNutritionalNotesService) {
        this.mealNutritionalNotesService = mealNutritionalNotesService;
    }
}
