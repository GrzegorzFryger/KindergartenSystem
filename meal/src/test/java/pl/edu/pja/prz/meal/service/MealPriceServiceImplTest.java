package pl.edu.pja.prz.meal.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.meal.exception.MealPriceListAlreadyExistException;
import pl.edu.pja.prz.meal.model.MealPrice;
import pl.edu.pja.prz.meal.model.enums.MealType;
import pl.edu.pja.prz.meal.repository.MealPriceRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MealPriceServiceImplTest {

    @Mock
    private MealPriceRepository mealPriceRepository;
    private MealPriceServiceImpl mealPriceService;

    @BeforeEach
    public void setup() {
        mealPriceService = new MealPriceServiceImpl(mealPriceRepository);
    }


    @Test
    void ShouldCreatMealPrice_When_InputArgumentIsCorrect() throws MealPriceListAlreadyExistException {
        //given
        MealPrice mealPrice = new MealPrice(1L, MealType.BREAKFAST, BigDecimal.ONE );
        when(mealPriceRepository.save(any())).thenReturn(mealPrice);
        //when
        MealPrice createdMealPrice = mealPriceService.creatMealPrice(mealPrice);
        //then
        assertEquals(createdMealPrice, mealPrice);

    }

    @Test
    void ShouldReturnMealPriceListAlreadyExistException_When_TriedToAddMealPriceButMealPriceListAlreadyExist() throws MealPriceListAlreadyExistException {
        //given
        MealPrice mealPrice = new MealPrice(1L, MealType.BREAKFAST, BigDecimal.ONE );
        when(mealPriceRepository.findByMealType(MealType.BREAKFAST)).thenReturn(Optional.of(mealPrice));

        //then
        assertThrows(MealPriceListAlreadyExistException.class, () -> {
            mealPriceService.creatMealPrice(mealPrice);
        });

    }

    @Test
    void updateMealPrice() {
    }

    @Test
    void getAllPrices() {
    }

    @Test
    void deleteMealPriceById() {
    }

    @Test
    void getPriceByMealType() {
    }
}
