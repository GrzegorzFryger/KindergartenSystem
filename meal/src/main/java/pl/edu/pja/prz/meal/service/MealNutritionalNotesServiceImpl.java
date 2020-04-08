package pl.edu.pja.prz.meal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.NutritionalNotes;
import pl.edu.pja.prz.meal.model.dto.NutritionalNotesDTO;
import pl.edu.pja.prz.meal.repository.MealNutritionalNotesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MealNutritionalNotesServiceImpl implements MealNutritionalNotesService {

    private MealNutritionalNotesRepository mealNutritionalNotesRepository;
    private MealService mealService;

    @Autowired
    public MealNutritionalNotesServiceImpl(MealNutritionalNotesRepository mealNutritionalNotesRepository, MealService mealService) {
        this.mealNutritionalNotesRepository = mealNutritionalNotesRepository;
        this.mealService = mealService;
    }

    @Override
    public List<NutritionalNotes> getAllNutritionalNotes() {
        return mealNutritionalNotesRepository.findAll();
    }

    @Override
    public List<NutritionalNotes> addNutritionalNotes(NutritionalNotesDTO nutritionalNotesDTO) {

        Meal meal = mealService.getMealByID(nutritionalNotesDTO.getMealId());
        List<NutritionalNotes> updatedList = meal.getNutritionalNotesList();
        updatedList.add( NutritionalNotes.create(nutritionalNotesDTO));

        Meal updatedMeal = mealService.updateMealNutritionalNotes(meal.getId(), updatedList);

        return updatedMeal.getNutritionalNotesList();
    }

    @Override
    public NutritionalNotes getNutritionalNotesById(Long id) {

        return mealNutritionalNotesRepository.findById(id).orElseThrow(() ->
                new ElementNotFoundException(id));
    }

    @Override
    public List<NutritionalNotes> deleteNutritionalNotesById(Long nnId, Long mealId) {

        if(mealService.isNoteByIdPresentInMeal(nnId, mealId)) {

            Meal meal = mealService.getMealByID(mealId);
            List<NutritionalNotes> nutritionalNotes = meal.getNutritionalNotesList();

            Optional<NutritionalNotes> objectToRemove = nutritionalNotes.stream()
                    .filter(u -> u.getId().equals(nnId))
                    .findFirst();

            if(objectToRemove.isPresent()) {
                nutritionalNotes.remove(objectToRemove.get());
                mealService.updateMealNutritionalNotes(mealId,nutritionalNotes);
                mealNutritionalNotesRepository.deleteById(nnId);
            }

        }
        return mealService.getNutritionalNotesByMealId(mealId);
    }
}
