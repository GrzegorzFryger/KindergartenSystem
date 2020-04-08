package pl.edu.pja.prz.meal.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.commons.exception.BusinessException;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.finances.facade.FinancesFacade;
import pl.edu.pja.prz.mail.facade.MailFacade;
import pl.edu.pja.prz.meal.exception.NotFoundException;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;
import pl.edu.pja.prz.meal.model.enums.DietType;
import pl.edu.pja.prz.meal.model.enums.MealStatus;
import pl.edu.pja.prz.meal.model.enums.MealType;
import pl.edu.pja.prz.meal.repository.MealConfigurationRepository;
import pl.edu.pja.prz.meal.repository.MealPriceRepository;
import pl.edu.pja.prz.meal.repository.MealRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MealServiceImplTest {

    @Mock
    private MealRepository mealRepository;
    @Mock
    private MealPriceRepository mealPriceListRepository;
    private MealServiceImpl mealService ;
    private MailFacade mailFacade;
    private FinancesFacade financesFacade;
    private MealConfigurationRepository mealConfigurationRepository;

    private MealCreateUpdateDTO mealCreateUpdateDTO;


    @BeforeEach
    public void setup() {
        mealService = new MealServiceImpl(mealRepository, new MealPriceServiceImpl(mealPriceListRepository), mailFacade, financesFacade, mealConfigurationRepository);

        mealCreateUpdateDTO =
                new MealCreateUpdateDTO(new BigDecimal(1), MealType.BREAKFAST, DietType.VEGAN, UUID.randomUUID(), LocalDate.MIN, LocalDate.MAX, new ArrayList<>());
    }


    @Test
    public void shouldCreateMeal_When_InputArgumentIsCorrect() {

        //given
        Meal meal = new Meal(new BigDecimal(99), LocalDateTime.MIN, LocalDateTime.MAX, DietType.VEGAN, MealStatus.ACTIVE, MealType.BREAKFAST, UUID.randomUUID(), new ArrayList<>());

        //when
        when(mealRepository.findMealByChildIDAndMealStatusAndMealType(any(), any(), any())).thenReturn(Optional.empty());
        when(mealRepository.save(any())).thenReturn(meal);
        Meal createdMeal = mealService.createMeal(mealCreateUpdateDTO);

        //then
        Assert.assertNotNull(createdMeal);
        Assert.assertEquals(MealStatus.ACTIVE, createdMeal.getMealStatus());
        Assert.assertNotNull(createdMeal.getMealPrice());
        Assert.assertNotNull(createdMeal.getChildID());
    }


    @Test
    public void shouldThrowMealActivityStatusException_When_TriedToCreateMealWhichIsAlreadyActive() {

        //given
        Meal meal = new Meal(new BigDecimal(99), LocalDateTime.MIN, LocalDateTime.MAX, DietType.VEGAN, MealStatus.ACTIVE, MealType.BREAKFAST, UUID.randomUUID(), new ArrayList<>());

        //when
        when(mealRepository.findMealByChildIDAndMealStatusAndMealType(any(), any(), any())).thenReturn(Optional.of(meal));

        //then
        assertThrows(BusinessException.class, () -> {
            mealService.createMeal(mealCreateUpdateDTO);
        });
    }
    @Test
    public void shouldGetMealByID_When_MealExists()  {
        //given
        when(mealRepository.findById(anyLong())).thenReturn(Optional.of(MealCreateUpdateDTO.createMealFactory(mealCreateUpdateDTO)));

        //when
        Meal meal = mealService.getMealByID(1L);

        //then
        Assert.assertNotNull(meal);

    }

    @Test
    public void shouldReturnNotFoundException_When_TriedToGetMealWithNotExist() {
        //given
        when(mealRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when
        assertThrows(ElementNotFoundException.class, () -> {
            mealService.getMealByID(1L);
        });
    }

    @Test
    public void shouldDeleteMealByID_When_MealExists() throws NotFoundException {
        //given
        when(mealRepository.existsById(anyLong())).thenReturn(true);

        //when
        mealService.deleteMealByID(1L);

        //then
        verify(mealRepository, times(1)).deleteById(any());
    }

    @Test
    public void shouldReturnNotFoundException_When_TriedToDeleteMealWithNotExist() {

        //when
        assertThrows(ElementNotFoundException.class, () -> {
            mealService.deleteMealByID(1L);
        });
    }

    @Test
    public void ShouldUpdateMeal_When_InputArgumentIsCorrect(){
        //given
        Meal meal = new Meal(new BigDecimal(88), LocalDateTime.MIN, LocalDateTime.now(), DietType.VEGAN, MealStatus.ACTIVE, MealType.SOUP, UUID.randomUUID(), new ArrayList<>());
        when(mealService.isMealPresentByID(1L)).thenReturn(true);
        when(mealRepository.findMealByIdAndMealStatus(any(), any())).thenReturn(Optional.empty());
        when(mealRepository.findById(1L)).thenReturn(Optional.of(meal));
        when(mealRepository.save(meal)).thenReturn(MealCreateUpdateDTO.createMealFactory(mealCreateUpdateDTO));

        //when
        Meal updatedMeal = mealService.updateMeal(mealCreateUpdateDTO, 1L);

        //then
        Assert.assertEquals(updatedMeal.getMealPrice(), mealCreateUpdateDTO.getMealPrice());
        Assert.assertEquals(updatedMeal.getMealType(), mealCreateUpdateDTO.getMealType());
        Assert.assertEquals(updatedMeal.getMealToDate(), LocalDateTime.of(mealCreateUpdateDTO.getMealToDate(), LocalTime.MIDNIGHT));
    }

    @Test
    public void ShouldReturnNotFoundException_When_TriedToUpdateMealWithNotExist() {
        //then
        assertThrows(ElementNotFoundException.class, () -> {
            mealService.updateMeal(mealCreateUpdateDTO, 1L);
        });
    }

    @Test
    public void ShouldReturnMealActivityStatusException_When_TriedToUpdateMealWithIsNotActive() {

        //given
        Meal meal = new Meal(BigDecimal.ONE, LocalDateTime.MIN, LocalDateTime.MAX, DietType.VEGAN, MealStatus.INACTIVE,  MealType.BREAKFAST, UUID.randomUUID(), new ArrayList<>() );
        when(mealRepository.existsById(1L)).thenReturn(true);
        when(mealRepository.findMealByIdAndMealStatus(1L, MealStatus.INACTIVE)).thenReturn(Optional.of(MealCreateUpdateDTO.createMealFactory(mealCreateUpdateDTO)));

        //then
        assertThrows(BusinessException.class, () -> {
            mealService.updateMeal(mealCreateUpdateDTO, 1L);
        });
    }

    @Test
    public void ShouldMarkMealAsInactiveOnDemand_When_InputArgumentIsCorrect(){
        //given
        when(mealRepository.existsById(1L)).thenReturn(true);
        when(mealRepository.findMealByIdAndMealStatus(any(), any())).thenReturn(Optional.empty());
        Meal meal = new Meal(BigDecimal.ONE, LocalDateTime.MIN, LocalDateTime.MAX, DietType.VEGAN, MealStatus.ACTIVE, MealType.BREAKFAST, UUID.randomUUID(), new ArrayList<>() );

        when(mealRepository.findById(1L)).thenReturn(Optional.of(meal));

        //when
        mealService.markMealAsInactiveOnDemand(1L);

        //then
        assertEquals(MealStatus.INACTIVE, meal.getMealStatus());
    }

    @Test
    public void ShouldReturnNotFoundException_When_TriedToMarkMealAsInactiveOnDemandWithNotExist() {
        //when
        assertThrows(ElementNotFoundException.class, () -> {
            mealService.markMealAsInactiveOnDemand(111L);
        });
    }

    @Test
    public void ShouldReturnMealActivityStatusException_When_TriedToMarkMealAsInactiveOnDemandWithIsNotActive() {
        //given
        when(mealRepository.existsById(111L)).thenReturn(true);
        Meal meal = new Meal(BigDecimal.ONE, LocalDateTime.MIN, LocalDateTime.MAX, DietType.VEGAN, MealStatus.ACTIVE, MealType.BREAKFAST, UUID.randomUUID(), new ArrayList<>() );
        when(mealRepository.findMealByIdAndMealStatus(any(), any())).thenReturn(Optional.of(meal));


        //then
        assertThrows(BusinessException.class, () -> {
            mealService.markMealAsInactiveOnDemand(111L);
        });
    }

    @Test
    public void ShouldGetAllMeals_When_InputArgumentIsCorrect() {
        //given
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(BigDecimal.ONE, LocalDateTime.MIN, LocalDateTime.MAX, DietType.VEGAN, MealStatus.ACTIVE, MealType.BREAKFAST, UUID.randomUUID(), new ArrayList<>() ));
        meals.add(new Meal(BigDecimal.ONE, LocalDateTime.MIN, LocalDateTime.MAX, DietType.VEGAN, MealStatus.INACTIVE, MealType.BREAKFAST, UUID.randomUUID(), new ArrayList<>() ));
        when(mealRepository.findAll()).thenReturn(meals);

        //when
        List<Meal> returnedMeals = mealService.getAllMeals();

        //then
        assertEquals(meals, returnedMeals);
    }

    @Test
    public void ShouldGetAllActiveMeals_When_InputArgumentIsCorrect() {
        //given
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(BigDecimal.ONE, LocalDateTime.MIN, LocalDateTime.MAX, DietType.VEGAN, MealStatus.ACTIVE, MealType.BREAKFAST, UUID.randomUUID(), new ArrayList<>() ));
        when(mealRepository.findAllByMealStatus(MealStatus.ACTIVE)).thenReturn(meals);

        //when
        List<Meal> returnedMeals = mealService.getAllActiveMeals();

        //then
        assertEquals(meals, returnedMeals);
    }

    @Test
    public void ShouldMarkMealsAsInactiveIfNeeded_When_InputArgumentIsCorrect() {
        //given
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(BigDecimal.ONE, LocalDateTime.MIN, LocalDateTime.MIN, DietType.VEGAN, MealStatus.ACTIVE, MealType.BREAKFAST, UUID.randomUUID(), new ArrayList<>() ));
        meals.add(new Meal(BigDecimal.ONE, LocalDateTime.MIN, LocalDateTime.MIN, DietType.VEGAN, MealStatus.ACTIVE, MealType.BREAKFAST, UUID.randomUUID(), new ArrayList<>() ));
        meals.add(new Meal(BigDecimal.ONE, LocalDateTime.MIN, LocalDateTime.MIN, DietType.VEGAN, MealStatus.ACTIVE, MealType.BREAKFAST, UUID.randomUUID(), new ArrayList<>() ));
        when(mealRepository.findAllByMealStatus(MealStatus.ACTIVE)).thenReturn(meals);

        //when
        List<Meal> afterInactiveMark = mealService.markMealsAsInactiveIfNeeded();

        //then
        assertEquals(MealStatus.INACTIVE, afterInactiveMark.get(1).getMealStatus());

    }

    @Test
    public void ShouldIsMealPresentByID_When_InputArgumentIsCorrect() {
        //when
        when(mealRepository.existsById(1L)).thenReturn(true);

        //then
        assertTrue(mealService.isMealPresentByID(1L));
    }
}
