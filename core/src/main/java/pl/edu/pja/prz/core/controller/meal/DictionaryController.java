package pl.edu.pja.prz.core.controller.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.prz.meal.facade.MealDictionaryFacade;
import pl.edu.pja.prz.meal.model.dto.DictionaryObjectDTO;

import java.util.List;

import static pl.edu.pja.prz.commons.constants.Roles.*;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_MEALS_DICTIONARY;

@RestController
@RequestMapping(API_MEALS_DICTIONARY)
public class DictionaryController {


    private final MealDictionaryFacade mealDictionaryFacade;

    @Autowired
    public DictionaryController(MealDictionaryFacade mealDictionaryFacade) {
        this.mealDictionaryFacade = mealDictionaryFacade;
    }


    @GetMapping("mealType")
    @PreAuthorize(HAS_ANY_ROLE)
    public List<DictionaryObjectDTO> getAllMealTypes() {
        return mealDictionaryFacade.getAllMealTypes();
    }

    @GetMapping("dietType")
    @PreAuthorize(HAS_ANY_ROLE)
    public List<DictionaryObjectDTO> getAllDietType() {
        return mealDictionaryFacade.getAllDietType();
    }




}
