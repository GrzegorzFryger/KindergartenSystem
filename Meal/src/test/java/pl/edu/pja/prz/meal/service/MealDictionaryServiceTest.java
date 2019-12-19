package pl.edu.pja.prz.meal.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import pl.edu.pja.prz.meal.model.dto.DictionaryObjectDTO;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MealDictionaryServiceTest {

    private MealDictionaryService mealDictionaryService = new MealDictionaryService();

    @Test
    public void getAllMealTypes() {
        //given
        List<DictionaryObjectDTO> dictionaryObjectDTOList;
        //when
        dictionaryObjectDTOList = mealDictionaryService.getAllMealTypes();
        //then
        Assert.assertNotNull(dictionaryObjectDTOList);
    }
}
