package pl.edu.pja.prz.meal.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import pl.edu.pja.prz.meal.model.dto.DictionaryObjectDTO;

import java.util.List;

import static org.junit.Assert.assertNotNull;


@RunWith(MockitoJUnitRunner.class)
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
