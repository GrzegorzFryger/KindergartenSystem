package pl.edu.pja.prz.meal.service;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.meal.model.dto.DictionaryObjectDTO;
import pl.edu.pja.prz.meal.model.enums.DietType;
import pl.edu.pja.prz.meal.model.enums.MealType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealDictionaryService {

    public List<DictionaryObjectDTO> getAllMealTypes() {
       return Arrays.stream(MealType.values())
               .map(u -> new DictionaryObjectDTO(u.name(), u.toString()))
               .collect(Collectors.toList());
    }

    public List<DictionaryObjectDTO> getAllDietTypes() {
        return Arrays.stream(DietType.values())
                .map(u -> new DictionaryObjectDTO(u.name(), u.toString()))
                .collect(Collectors.toList());
    }

}
