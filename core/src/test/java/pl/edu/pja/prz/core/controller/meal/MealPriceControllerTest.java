package pl.edu.pja.prz.core.controller.meal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.meal.model.MealPrice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MealPriceControllerTest {

    @Mock
    private MealPriceController mealPriceController;
    private MealPrice mealPrice;

    @Test
    void creatMealPrice() {
        //when
        mealPriceController.creatMealPrice(mealPrice);

        //then
        verify(mealPriceController, times(1)).creatMealPrice(mealPrice);
    }

    @Test
    void updateMealPrice() {
        //when
        mealPriceController.updateMealPrice(mealPrice, 1L);

        //then
        verify(mealPriceController, times(1)).updateMealPrice(mealPrice, 1L);
    }

    @Test
    void getAllPrices() {
        //when
        mealPriceController.getAllPrices();

        //then
        verify(mealPriceController, times(1)).getAllPrices();
    }

    @Test
    void deleteMealPriceById() {
        //when
        mealPriceController.deleteMealPriceById(1);

        //then
        verify(mealPriceController, times(1)).deleteMealPriceById(1);
    }
}
