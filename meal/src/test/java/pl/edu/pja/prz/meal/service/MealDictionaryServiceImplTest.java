package pl.edu.pja.prz.meal.service;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.meal.model.dto.DictionaryObjectDTO;

import java.util.List;

import static org.junit.Assert.assertNotNull;


@ExtendWith(MockitoExtension.class)
public class MealDictionaryServiceImplTest {

    private MealDictionaryServiceImpl mealDictionaryServiceImpl = new MealDictionaryServiceImpl();

    @Test
    public void Should_GetAllMealsType() {
        //given
        List<DictionaryObjectDTO> dictionaryObjectDTOList;
        //when
        dictionaryObjectDTOList = mealDictionaryServiceImpl.getAllMealTypes();
        //then
        assertNotNull(dictionaryObjectDTOList);
    }


}
