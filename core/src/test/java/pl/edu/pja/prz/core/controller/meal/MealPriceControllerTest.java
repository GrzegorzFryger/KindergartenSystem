package pl.edu.pja.prz.core.controller.meal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.meal.facade.MealPriceFacade;
import pl.edu.pja.prz.meal.model.MealPrice;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MealPriceControllerTest {


    private MealPriceController mealPriceController;
    private MealPrice mealPrice;

    @Mock
    private MealPriceFacade mealPriceFacade;

    @BeforeEach
    public void setUp() {
        mealPriceController = new MealPriceController(mealPriceFacade);
    }


    @Test
    void creatMealPrice() {
        //when
        mealPriceController.creatMealPrice(mealPrice);

        //then
        verify(mealPriceFacade, times(1)).creatMealPrice(mealPrice);
    }

    @Test
    void updateMealPrice() {
        //when
        mealPriceController.updateMealPrice(mealPrice, 1L);

        //then
        verify(mealPriceFacade, times(1)).updateMealPrice(mealPrice, 1L);
    }

    @Test
    void getAllPrices() {
        //when
        mealPriceController.getAllPrices();

        //then
        verify(mealPriceFacade, times(1)).getAllPrices();
    }

    @Test
    void deleteMealPriceById() {
        //when
        mealPriceController.deleteMealPriceById(1);

        //then
        verify(mealPriceFacade, times(1)).deleteMealPriceById(1);
    }
}
