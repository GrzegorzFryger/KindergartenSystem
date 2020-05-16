package pl.edu.pja.prz.meal.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.commons.exception.BusinessException;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.meal.model.MealPrice;
import pl.edu.pja.prz.meal.model.enums.MealType;
import pl.edu.pja.prz.meal.repository.MealPriceRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
    void ShouldCreatMealPrice_When_InputArgumentIsCorrect() {
        //given
        MealPrice mealPrice = new MealPrice(MealType.BREAKFAST, BigDecimal.ONE );
        when(mealPriceRepository.save(any())).thenReturn(mealPrice);
        //when
        MealPrice createdMealPrice = mealPriceService.creatMealPrice(mealPrice);
        //then
        assertEquals(createdMealPrice, mealPrice);

    }

    @Test
    void ShouldReturnMealPriceListAlreadyExistException_When_TriedToAddMealPriceButMealPriceListAlreadyExist() {
        //given
        MealPrice mealPrice = new MealPrice(MealType.BREAKFAST, BigDecimal.ONE );
        when(mealPriceRepository.findByMealType(MealType.BREAKFAST)).thenReturn(Optional.of(mealPrice));

        //then
        assertThrows(BusinessException.class, () -> {
            mealPriceService.creatMealPrice(mealPrice);
        });

    }

    @Test
    void ShouldUpdateMealPrice_When_InputArgumentIsCorrect() {
        //given
        MealPrice updatedMealPrice = new MealPrice(MealType.BREAKFAST, BigDecimal.TEN );
        MealPrice mealPrice = new MealPrice(MealType.BREAKFAST, BigDecimal.ONE );
        when(mealPriceRepository.findById(2L)).thenReturn(Optional.of(mealPrice));

        //when
        mealPriceService.updateMealPrice(updatedMealPrice, 2L);

        //then
        assertEquals(BigDecimal.TEN, mealPrice.getMealPrice());
    }

    @Test
    void ShouldNotFoundException_When_MealPriceNotFound() {
        //given
        when(mealPriceRepository.findById(2L)).thenReturn(Optional.empty());
        MealPrice updatedMealPrice = new MealPrice(MealType.BREAKFAST, BigDecimal.TEN );

        //then
        assertThrows(ElementNotFoundException.class, () -> {
            mealPriceService.updateMealPrice(updatedMealPrice, 2L);
        });

    }

    @Test
    void ShouldGetAllPrices_When_InputArgumentIsCorrect() {
        //given
        List<MealPrice> mealPriceList = new ArrayList<>();
        mealPriceList.add(new MealPrice(MealType.BREAKFAST, BigDecimal.TEN ));
        when(mealPriceRepository.findAll()).thenReturn(mealPriceList);

        //when
        List<MealPrice> returnedMealPrice = mealPriceService.getAllPrices();

        //then
        assertEquals(mealPriceList, returnedMealPrice);
    }

    @Test
    void ShouldReturnNotFoundException_When_TriedToDeleteMealPriceWithNotExist() {
        //given
        when(mealPriceRepository.existsById(1L)).thenReturn(false);
        //then
        assertThrows(ElementNotFoundException.class, () -> {
            mealPriceService.deleteMealPriceById(1L);
        });
    }

    @Test
    void ShouldGetPriceByMealType_When_InputArgumentIsCorrect() {
        //given
        when(mealPriceRepository.findMealPriceByMealType(MealType.BREAKFAST)).thenReturn(22.22);

        //when
        BigDecimal price = mealPriceService.getPriceByMealType(MealType.BREAKFAST);

        //then
        assertEquals(price, BigDecimal.valueOf(22.22));
    }

    @Test
    void ShouldGetMealPriceById_When_InputArgumentIsCorrect() {
        //given
        when(mealPriceRepository.findById(anyLong())).thenReturn(Optional.of(new MealPrice()));

        //when
        MealPrice mealPrice = mealPriceService.getById(1L);

        //then
        assertNotNull(mealPrice);
    }

    @Test
    void getAllNotSetMealPrice_When_InputArgumentIsCorrect() {
        //given
        when(mealPriceService.getAllPrices()).thenReturn(new ArrayList<>());


        //when
        List<MealType> mealTypes = mealPriceService.getAllNotSetMealPrice();

        //then
        assertNotNull(mealTypes);
        assertEquals(3, mealTypes.size());

    }
}
