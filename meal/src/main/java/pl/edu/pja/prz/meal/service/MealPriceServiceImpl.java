package pl.edu.pja.prz.meal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.meal.exception.MealPriceListAlreadyExistException;
import pl.edu.pja.prz.meal.exception.NotFoundException;
import pl.edu.pja.prz.meal.model.MealPrice;
import pl.edu.pja.prz.meal.model.enums.MealType;
import pl.edu.pja.prz.meal.repository.MealPriceRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MealPriceServiceImpl {


    private MealPriceRepository mealPriceRepository;

    @Autowired
    public MealPriceServiceImpl(MealPriceRepository mealPriceRepository) {
        this.mealPriceRepository = mealPriceRepository;
    }

    public MealPrice creatMealPrice(MealPrice mealPrice) throws MealPriceListAlreadyExistException {
        if (mealPriceRepository.findByMealType(mealPrice.getMealType()).isPresent()) {
            throw new MealPriceListAlreadyExistException("Price list to " + mealPrice.getMealType() + " already exist");
        }
        return mealPriceRepository.save(mealPrice);
    }

    public MealPrice updateMealPrice(MealPrice mealPrice, long id) throws NotFoundException {
        Optional<MealPrice> mealPriceOptional = mealPriceRepository.findById(id);
        if (mealPriceOptional.isEmpty()) {
            throw new NotFoundException("Price list with ID:" + id + " not found");
        }

        MealPrice priceToUpdate = mealPriceOptional.get();
        priceToUpdate.setMealPrice(mealPrice.getMealPrice());
        return mealPriceRepository.save(priceToUpdate);
    }

    public List<MealPrice> getAllPrices() {
        return mealPriceRepository.findAll();
    }

    public void deleteMealPriceById(long id) throws NotFoundException {
        if (!mealPriceRepository.existsById(id)) {
            throw new NotFoundException("Price list with ID:" + id + " not found");
        }
        mealPriceRepository.deleteById(id);
    }

    BigDecimal getPriceByMealType(MealType mealType) {
        return BigDecimal.valueOf(mealPriceRepository.findMealPriceByMealType(mealType));
    }

}
