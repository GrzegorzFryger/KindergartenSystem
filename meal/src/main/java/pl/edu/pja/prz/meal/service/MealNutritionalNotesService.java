package pl.edu.pja.prz.meal.service;

import pl.edu.pja.prz.meal.model.NutritionalNotes;
import pl.edu.pja.prz.meal.model.dto.NutritionalNotesDTO;

import java.util.List;

public interface MealNutritionalNotesService {

    List<NutritionalNotes> getAllNutritionalNotes();
    List<NutritionalNotes> addNutritionalNotes(NutritionalNotesDTO nutritionalNotesDTO);
    NutritionalNotes getNutritionalNotesById(Long id);
    void deleteNutritionalNotesById(Long id);

}
