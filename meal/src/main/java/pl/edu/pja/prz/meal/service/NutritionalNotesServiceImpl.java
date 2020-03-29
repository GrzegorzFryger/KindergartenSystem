package pl.edu.pja.prz.meal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.meal.model.NutritionalNotes;
import pl.edu.pja.prz.meal.model.dto.NutritionalNotesDTO;
import pl.edu.pja.prz.meal.repository.MealNutritionalNotesRepository;

import java.util.List;

@Service
public class NutritionalNotesServiceImpl implements NutritionalNotesService{

    private MealNutritionalNotesRepository mealNutritionalNotesRepository;
    private MealService mealService;

    @Autowired
    public NutritionalNotesServiceImpl(MealNutritionalNotesRepository mealNutritionalNotesRepository, MealService mealService) {
        this.mealNutritionalNotesRepository = mealNutritionalNotesRepository;
        this.mealService = mealService;
    }

    @Override
    public List<NutritionalNotes> getAllNutritionalNotes() {
        return mealNutritionalNotesRepository.findAll();
    }

    @Override
    public NutritionalNotes addNutritionalNotes(NutritionalNotesDTO nutritionalNotesDTO) {

    }

    @Override
    public NutritionalNotes getNutritionalNotesById(Long id) {
        return null;
    }

    @Override
    public void deleteNutritionalNotesById(Long id) {

    }
}
