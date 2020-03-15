package pl.edu.pja.prz.core.controller.meal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DictionaryControllerTest {

    @Mock
    private DictionaryController dictionaryController;

    @Test
    void getAllMealTypes() {
        //when
        dictionaryController.getAllMealTypes();

        //then
        verify(dictionaryController, times(1)).getAllMealTypes();
    }

    @Test
    void getAllDietType() {
        //when
        dictionaryController.getAllDietType();

        //then
        verify(dictionaryController, times(1)).getAllDietType();
    }
}
