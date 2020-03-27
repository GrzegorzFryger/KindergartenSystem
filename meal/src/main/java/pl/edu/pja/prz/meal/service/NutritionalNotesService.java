package pl.edu.pja.prz.meal.service;

import pl.edu.pja.prz.meal.model.NutritionalNotes;
import pl.edu.pja.prz.meal.model.dto.NutritionalNotesDTO;

import java.util.List;

public interface NutritionalNotesService {

    List<NutritionalNotes> getAllNutritionalNotes();
    NutritionalNotes addNutritionalNotes(NutritionalNotesDTO nutritionalNotesDTO);
    NutritionalNotes getNutritionalNotesById(Long id);
    void deleteNutritionalNotesById(Long id);

}
