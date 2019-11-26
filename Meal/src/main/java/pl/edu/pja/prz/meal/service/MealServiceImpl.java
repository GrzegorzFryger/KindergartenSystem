package pl.edu.pja.prz.meal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.meal.exception.NotFound;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;
import pl.edu.pja.prz.meal.repository.MealRepository;

import java.util.Optional;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository mealRepository;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public Meal createMate(MealCreateUpdateDTO meal) {
        return null;
    }

    @Override
    public Meal getMealByID(Long id) throws NotFound {
        return mealRepository.findById(id).orElseThrow(() -> new NotFound("Meal by ID " + id + "doest exist"));
    }

    @Override
    public void deleteMealByID(Long id) throws NotFound {
        if(isMealPresentByID(id)) {
            mealRepository.deleteById(id);
        }
        else throw new NotFound("Meal by ID " + id + "doest exist");
    }

    @Override
    public Optional<Meal> updateMeal(MealCreateUpdateDTO meal, Long mealToUpdateID) throws NotFound {
        if(isMealPresentByID(mealToUpdateID)) {
            return null;
        }
        else throw new NotFound("Meal by ID " + mealToUpdateID + "doest exist");


    }

    public boolean isMealPresentByID(Long id) {
        return mealRepository.existsById(id);
    }
}
