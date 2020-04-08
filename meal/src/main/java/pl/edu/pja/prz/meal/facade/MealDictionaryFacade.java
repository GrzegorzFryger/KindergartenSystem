package pl.edu.pja.prz.meal.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.meal.model.dto.DictionaryObjectDTO;
import pl.edu.pja.prz.meal.service.MealDictionaryServiceImpl;

import java.util.List;

@Service
public class MealDictionaryFacade {

    private final MealDictionaryServiceImpl mealDictionaryServiceImpl;

    @Autowired
    public MealDictionaryFacade(MealDictionaryServiceImpl mealDictionaryServiceImpl) {
        this.mealDictionaryServiceImpl = mealDictionaryServiceImpl;
    }

    public List<DictionaryObjectDTO> getAllMealTypes() {
        return mealDictionaryServiceImpl.getAllMealTypes();
    }

    public List<DictionaryObjectDTO> getAllDietType() {
        return mealDictionaryServiceImpl.getAllDietTypes();
    }
}
