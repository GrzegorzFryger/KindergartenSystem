package pl.edu.pja.prz.meal.service;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.meal.model.dto.DictionaryObjectDTO;

import java.util.List;

import static org.junit.Assert.assertNotNull;


@ExtendWith(MockitoExtension.class)
public class MealDictionaryServiceTest {

    private MealDictionaryService mealDictionaryService = new MealDictionaryService();

    @Test
    public void Should_GetAllMealsType() {
        //given
        List<DictionaryObjectDTO> dictionaryObjectDTOList;
        //when
        dictionaryObjectDTOList = mealDictionaryService.getAllMealTypes();
        //then
        assertNotNull(dictionaryObjectDTOList);
    }


}
