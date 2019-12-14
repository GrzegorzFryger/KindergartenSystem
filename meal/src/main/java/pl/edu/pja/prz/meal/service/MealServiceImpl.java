package pl.edu.pja.prz.meal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.meal.exception.MeaActivityStatusException;
import pl.edu.pja.prz.meal.exception.NotFound;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;
import pl.edu.pja.prz.meal.model.enums.MealStatus;
import pl.edu.pja.prz.meal.repository.MealRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository mealRepository;
    private MealPriceListServiceImpl mealPriceListService;


    @Autowired
    public MealServiceImpl(MealRepository mealRepository, MealPriceListServiceImpl mealPriceListService) {
        this.mealRepository = mealRepository;
        this.mealPriceListService = mealPriceListService;
    }

    @Override
    public Meal createMeal(MealCreateUpdateDTO meal) throws MeaActivityStatusException {
        if(mealRepository.findMealByChildIDAndMealStatus(meal.getChildID(), MealStatus.ACTIVE).isEmpty()) {
            meal.setMealPrice(mealPriceListService.getPriceByMealType(meal.getMealType()));
            return mealRepository.save(MealCreateUpdateDTO.createMealFactory(meal));
        }
        else throw new MeaActivityStatusException("There is already meal with status ACTIVE for child with ID: " + meal.getChildID());
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
    public Meal updateMeal(MealCreateUpdateDTO meal, Long mealToUpdateID) throws NotFound, MeaActivityStatusException {
        if(!isMealPresentByID(mealToUpdateID)) {
            throw new NotFound("Meal by ID " + mealToUpdateID + "doest exist");
        }
        if(mealRepository.findMealByIdAndMealStatus(mealToUpdateID, MealStatus.ACTIVE).isPresent()) {
            throw new MeaActivityStatusException("Meal with ID: "+ mealToUpdateID +" is not ACTIVE");
        }
        
        Meal mealToUpdate = getMealByID(mealToUpdateID);
        mealToUpdate.setMealPrice(meal.getMealPrice());
        mealToUpdate.setMealType(meal.getMealType());
        mealToUpdate.setMealToDate(LocalDateTime.of(meal.getMealToDate(), LocalTime.MIDNIGHT));
        
        return mealRepository.save(mealToUpdate);
    }

    @Override
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }
    
    public List<Meal> getAllActiveMeals() {
        return mealRepository.findAllByMealStatus(MealStatus.ACTIVE);
    }
    
    public void markMealsAsInactiveIfNeeded() {
        List<Meal> allMealsWithActiveStatus = mealRepository.findAllByMealStatus(MealStatus.ACTIVE);
        allMealsWithActiveStatus.forEach(u -> {
            if(u.getMealToDate().isAfter(LocalDateTime.now())) {
                u.setMealStatus(MealStatus.INACTIVE);
                mealRepository.save(u);
            }
        });
    }

    public boolean isMealPresentByID(Long id) {
        return mealRepository.existsById(id);
    }
}
