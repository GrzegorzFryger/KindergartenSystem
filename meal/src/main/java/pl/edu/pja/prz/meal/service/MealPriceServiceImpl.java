package pl.edu.pja.prz.meal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.BusinessException;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.meal.model.MealPrice;
import pl.edu.pja.prz.meal.model.enums.MealType;
import pl.edu.pja.prz.meal.repository.MealPriceRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MealPriceServiceImpl implements MealPriceService {


    private final MealPriceRepository mealPriceRepository;

    @Autowired
    public MealPriceServiceImpl(MealPriceRepository mealPriceRepository) {
        this.mealPriceRepository = mealPriceRepository;
    }

    public MealPrice creatMealPrice(MealPrice mealPrice) {
        if (mealPriceRepository.findByMealType(mealPrice.getMealType()).isPresent()) {
            throw new BusinessException("Price list to " + mealPrice.getMealType() + " already exist");
        }
        return mealPriceRepository.save(mealPrice);
    }

    public MealPrice updateMealPrice(MealPrice mealPrice, long id) {
        Optional<MealPrice> mealPriceOptional = mealPriceRepository.findById(id);
        if (mealPriceOptional.isEmpty()) {
            throw new ElementNotFoundException(id);
        }

        MealPrice priceToUpdate = mealPriceOptional.get();
        priceToUpdate.setMealPrice(mealPrice.getMealPrice());
        return mealPriceRepository.save(priceToUpdate);
    }

    public List<MealPrice> getAllPrices() {
        return mealPriceRepository.findAll();
    }

    public void deleteMealPriceById(long id) {
        if (!mealPriceRepository.existsById(id)) {
            throw new ElementNotFoundException(id);
        }
        mealPriceRepository.deleteById(id);
    }

    public BigDecimal getPriceByMealType(MealType mealType) {
        return BigDecimal.valueOf(mealPriceRepository.findMealPriceByMealType(mealType));
    }

}
