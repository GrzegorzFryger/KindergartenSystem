package pl.edu.pja.prz.meal.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.meal.model.dto.NutritionalNotesDTO;
import pl.edu.pja.prz.meal.service.MealNutritionalNotesService;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MealNutritionalNotesFacadeTest {


    @Mock
    private MealNutritionalNotesService mealNutritionalNotesService;
    private MealNutritionalNotesFacade mealNutritionalNotesFacade;



    @BeforeEach
    void setUp() {
        mealNutritionalNotesFacade = new MealNutritionalNotesFacade(mealNutritionalNotesService);
    }

    @Test
    void ShouldGetAllNutritionalNotes_When_CorrectCall() {
        //when
        mealNutritionalNotesFacade.getAllNutritionalNotes();
        //then
        verify(mealNutritionalNotesService, times(1)).getAllNutritionalNotes();
    }

    @Test
    void ShouldAddNutritionalNotes_When_CorrectCall() {
        //given
        NutritionalNotesDTO dto = new NutritionalNotesDTO();
        dto.setCreatedTime(LocalDateTime.now());
        dto.setMealId(1L);
        dto.setNutritionalNotesValue("Some Nutritional Notes Value");

        //when
        mealNutritionalNotesFacade.addNutritionalNotes(dto);
        //then
        verify(mealNutritionalNotesService, times(1))
                .addNutritionalNotes(any(NutritionalNotesDTO.class));
    }

    @Test
    void ShouldGetNutritionalNotesById_When_CorrectCall() {
        //when
        mealNutritionalNotesFacade.getNutritionalNotesById(anyLong());
        //then
        verify(mealNutritionalNotesService, times(1)).getNutritionalNotesById(anyLong());
    }

    @Test
    void ShouldDeleteNutritionalNotesById_When_CorrectCall() {
        //when
        mealNutritionalNotesFacade.deleteNutritionalNotesById(anyLong(),anyLong());
        //then
        verify(mealNutritionalNotesService, times(1)).deleteNutritionalNotesById(anyLong(), anyLong());
    }
}
