package pl.edu.pja.prz.core.controller.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.prz.meal.facade.MealDictionaryFacade;
import pl.edu.pja.prz.meal.model.dto.DictionaryObjectDTO;

import java.util.List;

@RestController
@RequestMapping("api/dictionary/meal/")
//TODO: ADD @PreAuthorize annotation with proper roles from Roles.java class
public class DictionaryController {


    private final MealDictionaryFacade mealDictionaryFacade;

    @Autowired
    public DictionaryController(MealDictionaryFacade mealDictionaryFacade) {
        this.mealDictionaryFacade = mealDictionaryFacade;
    }


    @GetMapping("mealType")
    public List<DictionaryObjectDTO> getAllMealTypes() {
        return mealDictionaryFacade.getAllMealTypes();
    }

    @GetMapping("dieType")
    public List<DictionaryObjectDTO> getAllDietType() {
        return mealDictionaryFacade.getAllDietType();
    }




}
