package pl.edu.pja.prz.meal.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.meal.exception.MealPriceListAlreadyExistException;
import pl.edu.pja.prz.meal.exception.NotFoundException;
import pl.edu.pja.prz.meal.model.MealPrice;
import pl.edu.pja.prz.meal.model.enums.MealType;
import pl.edu.pja.prz.meal.service.MealPriceServiceImpl;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MealPriceFacadeTest {

    @Mock
    private MealPriceServiceImpl mealPriceService;

    private MealPriceFacade mealPriceFacade;
    private MealPrice mealPrice;

    @BeforeEach
    public void setUp() {
        mealPriceFacade = new MealPriceFacade(mealPriceService);
        mealPrice = new MealPrice(MealType.BREAKFAST, BigDecimal.valueOf(22.22));
    }

    @Test
    public void Should_CreatMealPriceFacade() throws MealPriceListAlreadyExistException {

        //when
        mealPriceFacade.creatMealPrice(mealPrice);
        //then
        verify(mealPriceService, times(1)).creatMealPrice(mealPrice);
    }

    @Test
    public void Should_UpdateMealPriceFacade() throws NotFoundException {

        //when
        mealPriceFacade.updateMealPrice(mealPrice, 1);
        //then
        verify(mealPriceService, times(1)).updateMealPrice(mealPrice, 1);
    }

    @Test
    public void Should_GetAllPricesFacade() {
        //when
        mealPriceFacade.getAllPrices();
        //then
        verify(mealPriceService, times(1)).getAllPrices();
    }

    @Test
    public void Should_DeleteMealPriceByIdFacade() throws NotFoundException {
        //when
        mealPriceFacade.deleteMealPriceById(1);
        //then
        verify(mealPriceService, times(1)).deleteMealPriceById(1);
    }



}
