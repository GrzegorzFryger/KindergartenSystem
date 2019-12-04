package pl.edu.pja.prz.meal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.meal.exception.MealPriceListAlreadyExistException;
import pl.edu.pja.prz.meal.exception.NotFound;
import pl.edu.pja.prz.meal.model.MealPriceList;
import pl.edu.pja.prz.meal.repository.MealPriceListRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MealPriceListServiceImpl {


    private MealPriceListRepository mealPriceListRepository;

    @Autowired
    public MealPriceListServiceImpl(MealPriceListRepository mealPriceListRepository) {
        this.mealPriceListRepository = mealPriceListRepository;
    }

    public MealPriceList creatMealPriceList(MealPriceList mealPriceList) throws MealPriceListAlreadyExistException {
        if(mealPriceListRepository.findByMealType(mealPriceList.getMealType()).isPresent()) {
            throw new MealPriceListAlreadyExistException("Price list to " + mealPriceList.getMealType() + " already exist");
        }
        return mealPriceListRepository.save(mealPriceList);
    }

    public MealPriceList updateMealPriceList(MealPriceList mealPriceList, long id) throws NotFound {
        Optional<MealPriceList> mealPriceListOptional = mealPriceListRepository.findById(id);
        if(mealPriceListOptional.isEmpty()){
            throw new NotFound("Price list with ID:" + id +" not found");
        }

        MealPriceList priceListToUpdate = mealPriceListOptional.get();
        priceListToUpdate.setMealPrice(mealPriceList.getMealPrice());
        return mealPriceListRepository.save(priceListToUpdate);
    }

    public List<MealPriceList> getAllPriceList() {
        return mealPriceListRepository.findAll();
    }

    public void deleteMealPriceList(long id) throws NotFound {
        if(!mealPriceListRepository.existsById(id)) {
            throw new NotFound("Price list with ID:" + id +" not found");
        }
        mealPriceListRepository.deleteById(id);
    }

}
