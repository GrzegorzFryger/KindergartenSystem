package pl.edu.pja.prz.meal.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.meal.model.dto.DictionaryObjectDTO;
import pl.edu.pja.prz.meal.service.MealDictionaryService;

import java.util.List;

@Service
public class MealDictionaryFacade {

    private final MealDictionaryService mealDictionaryService;

    @Autowired
    public MealDictionaryFacade(MealDictionaryService mealDictionaryService) {
        this.mealDictionaryService = mealDictionaryService;
    }

    public List<DictionaryObjectDTO> getAllMealTypes() {
        return mealDictionaryService.getAllMealTypes();
    }

    public List<DictionaryObjectDTO> getAllDietType() {
        return mealDictionaryService.getAllDietTypes();
    }
}
