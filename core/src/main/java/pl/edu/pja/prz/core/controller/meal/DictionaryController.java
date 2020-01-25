package pl.edu.pja.prz.core.controller.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.prz.meal.facade.MealDictionaryFacade;

@RestController
@RequestMapping("api/dictionary/meal/")
public class DictionaryController {


    private final MealDictionaryFacade mealDictionaryFacade;

    @Autowired
    public DictionaryController(MealDictionaryFacade mealDictionaryFacade) {
        this.mealDictionaryFacade = mealDictionaryFacade;
    }


}
