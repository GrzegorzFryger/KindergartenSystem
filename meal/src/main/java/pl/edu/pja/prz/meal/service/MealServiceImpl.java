package pl.edu.pja.prz.meal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.BusinessException;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.finances.facade.FinancesFacade;
import pl.edu.pja.prz.mail.facade.MailFacade;
import pl.edu.pja.prz.mail.model.BaseMail;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.MealConfiguration;
import pl.edu.pja.prz.meal.model.NutritionalNotes;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;
import pl.edu.pja.prz.meal.model.enums.MealStatus;
import pl.edu.pja.prz.meal.repository.MealConfigurationRepository;
import pl.edu.pja.prz.meal.repository.MealRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@ConfigurationProperties(prefix = "meal")
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final MealPriceServiceImpl mealPriceListService;
    private final MailFacade mailFacade;
    private final FinancesFacade financesFacade;
    private final MealConfigurationRepository mealConfigurationRepository;


    @Autowired
    public MealServiceImpl(MealRepository mealRepository, MealPriceServiceImpl mealPriceListService,
                           MailFacade mailFacade, FinancesFacade financesFacade, MealConfigurationRepository mealConfigurationRepository) {
        this.mealRepository = mealRepository;
        this.mealPriceListService = mealPriceListService;
        this.mailFacade = mailFacade;
        this.financesFacade = financesFacade;
        this.mealConfigurationRepository = mealConfigurationRepository;
    }

    @Override
    public Meal createMeal(MealCreateUpdateDTO meal)  {
        if (mealRepository.findMealByChildIDAndMealStatusAndMealType(meal.getChildID(), MealStatus.ACTIVE, meal.getMealType()).isEmpty()) {
            meal.setMealPrice(mealPriceListService.getPriceByMealType(meal.getMealType()));
            return mealRepository.save(MealCreateUpdateDTO.createMealFactory(meal));
        } else
            throw new BusinessException ("There is already meal with status ACTIVE and type " +
                    meal.getMealType() + " for child with ID: " + meal.getChildID());
    }

    @Override
    public Meal getMealByID(Long id) {
        return mealRepository.findById(id).orElseThrow(() -> new ElementNotFoundException (id));
    }

    @Override
    public void deleteMealByID(Long id) {
        if (isMealPresentByID(id)) {
            mealRepository.deleteById(id);
        } else throw new ElementNotFoundException (id);
    }

    @Override
    public Meal updateMeal(MealCreateUpdateDTO meal, Long mealToUpdateID) {
        if (!isMealPresentByID(mealToUpdateID)) {
            throw new ElementNotFoundException (mealToUpdateID);
        }
        if (mealRepository.findMealByIdAndMealStatus(mealToUpdateID, MealStatus.INACTIVE).isPresent()) {
            throw new BusinessException ("Meal with ID: " + mealToUpdateID + " is not ACTIVE");
        }

        Meal mealToUpdate = getMealByID(mealToUpdateID);
        if (meal.getMealPrice() != null) {
            mealToUpdate.setMealPrice(meal.getMealPrice());
        }
        if (meal.getMealType() != null) {
            mealToUpdate.setMealTypes(meal.getMealType());
        }
        if (meal.getMealToDate() != null) {
            mealToUpdate.setMealToDate(LocalDateTime.of(meal.getMealToDate(), LocalTime.MIDNIGHT));
        }


        return mealRepository.save(mealToUpdate);
    }

    public Meal markMealAsInactiveOnDemand(Long mealToMarkAsInactiveId) {
        if (!isMealPresentByID(mealToMarkAsInactiveId)) {
            throw new ElementNotFoundException (mealToMarkAsInactiveId);
        }

        if (mealRepository.findMealByIdAndMealStatus(mealToMarkAsInactiveId, MealStatus.ACTIVE).isPresent()) {
            throw new BusinessException ("Meal with ID: " + mealToMarkAsInactiveId + " is not ACTIVE");
        }

        Meal mealToMarkAsInactive = getMealByID(mealToMarkAsInactiveId);
        mealToMarkAsInactive.setMealStatus(MealStatus.INACTIVE);
        return mealRepository.save(mealToMarkAsInactive);

    }

    @Override
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    public List<Meal> getAllActiveMeals() {
        return mealRepository.findAllByMealStatus(MealStatus.ACTIVE);
    }

    public List<Meal> markMealsAsInactiveIfNeeded() {
        List<Meal> allMealsWithActiveStatus = mealRepository.findAllByMealStatus(MealStatus.ACTIVE);
        allMealsWithActiveStatus.forEach(u -> {
            if (u.getMealToDate().isBefore(LocalDateTime.now())) {
                u.setMealStatus(MealStatus.INACTIVE);
                mealRepository.save(u);
            }
        });
        return allMealsWithActiveStatus;
    }

    public boolean isMealPresentByID(Long id) {
        return mealRepository.existsById(id);
    }

    private List<String> prepareDataToSendViaMail() {
        List<Meal> activeMeals = getAllActiveMeals();
        Map<String, Integer> mealsCount = new HashMap<>();

        activeMeals.forEach(u -> {
            String mealToOrder = u.getMealType() + " (" + u.getDietType()+")";
            if(!mealsCount.containsKey(mealToOrder)){
                mealsCount.put(mealToOrder, 0);
            }
            mealsCount.put(mealToOrder, mealsCount.get(mealToOrder) + 1);
        });
        return mealsCount.keySet().stream().map(u -> u + " : " + mealsCount.get(u))
                .collect(Collectors.toList());
    }

    public void sendEmailWithOrder() {
        MealConfiguration config = mealConfigurationRepository.getOne(1L);
        BaseMail baseMail = new BaseMail();
        baseMail.setTo(config.getEmailToSendMealOrder());
        baseMail.setSubject("Zamówienie na dzień " + LocalDate.now());
        String content = prepareDataToSendViaMail().stream().map(u -> u + '\n').collect(Collectors.joining());
        baseMail.setContent(content);
        mailFacade.sendEmail(baseMail);
    }

    public void chargeForMeal() {
        List<Meal> activeMeals = getAllActiveMeals();

        activeMeals.forEach(u -> {
            financesFacade.decreaseBalance(u.getChildID(), u.getMealPrice(), u.getMealType() + "w dniu " + LocalDate.now() );
        });
    }

    public Meal updateMealNutritionalNotes(Long mealId, List<NutritionalNotes> nutritionalNotes){
        Meal mealToUpdate = getMealByID(mealId);
        mealToUpdate.setNutritionalNotesList(nutritionalNotes);
        return mealRepository.save(mealToUpdate);
    }

    public boolean isNoteByIdPresentInMeal(Long nnId, Long mealId) {
        return getMealByID(mealId).getNutritionalNotesList()
                .stream().anyMatch(u -> u.getId().equals(nnId));
    }

    public List<NutritionalNotes> getNutritionalNotesByMealId(Long mealId) {
        return getMealByID(mealId).getNutritionalNotesList();
    }

}
