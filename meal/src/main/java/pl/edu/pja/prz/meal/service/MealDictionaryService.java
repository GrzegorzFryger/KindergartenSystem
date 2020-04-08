package pl.edu.pja.prz.meal.service;

import pl.edu.pja.prz.meal.model.dto.DictionaryObjectDTO;

import java.util.List;

public interface MealDictionaryService {

    List<DictionaryObjectDTO> getAllMealTypes();
    List<DictionaryObjectDTO> getAllDietTypes();
}
