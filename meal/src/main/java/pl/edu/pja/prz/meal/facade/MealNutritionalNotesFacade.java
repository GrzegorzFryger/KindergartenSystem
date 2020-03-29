package pl.edu.pja.prz.meal.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.meal.model.NutritionalNotes;
import pl.edu.pja.prz.meal.model.dto.NutritionalNotesDTO;
import pl.edu.pja.prz.meal.service.MealNutritionalNotesService;

import java.util.List;

@Service
public class MealNutritionalNotesFacade {

    private final MealNutritionalNotesService mealNutritionalNotesService;

    @Autowired
    public MealNutritionalNotesFacade(MealNutritionalNotesService mealNutritionalNotesService) {
        this.mealNutritionalNotesService = mealNutritionalNotesService;
    }

    public List<NutritionalNotes> getAllNutritionalNotes() {
        return mealNutritionalNotesService.getAllNutritionalNotes();
    }

    public List<NutritionalNotes> addNutritionalNotes(NutritionalNotesDTO nutritionalNotesDTO) {
        return mealNutritionalNotesService.addNutritionalNotes(nutritionalNotesDTO);
    }

    public NutritionalNotes getNutritionalNotesById(Long id) {
        return mealNutritionalNotesService.getNutritionalNotesById(id);
    }

    public void deleteNutritionalNotesById(Long id) {
        mealNutritionalNotesService.deleteNutritionalNotesById(id);
    }
}
