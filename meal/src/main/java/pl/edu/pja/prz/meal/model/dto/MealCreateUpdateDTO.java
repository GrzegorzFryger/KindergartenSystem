package pl.edu.pja.prz.meal.model.dto;

import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.enums.DietType;
import pl.edu.pja.prz.meal.model.enums.MealStatus;
import pl.edu.pja.prz.meal.model.enums.MealType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class MealCreateUpdateDTO {

	private BigDecimal mealPrice;
	private MealType mealType;
	private DietType dietType;
	private UUID childID;
	private LocalDate mealFromDate;
	private LocalDate mealToDate;

	public MealCreateUpdateDTO(BigDecimal mealPrice, MealType mealType, DietType dietType, UUID childID,
							   LocalDate mealFromDate, LocalDate mealToDate) {
		this.mealPrice = mealPrice;
		this.mealType = mealType;
		this.childID = childID;
		this.dietType = dietType;
		this.mealFromDate = mealFromDate;
		this.mealToDate = mealToDate;
	}

	public static Meal createMealFactory(MealCreateUpdateDTO dto) {
		return new Meal(dto.getMealPrice(),
				LocalDateTime.of(dto.getMealFromDate(), LocalTime.NOON),
				LocalDateTime.of(dto.getMealToDate(), LocalTime.MIDNIGHT),
				dto.getDietType(),
				MealStatus.ACTIVE,
				dto.getMealType(),
				dto.getChildID());
	}

	public BigDecimal getMealPrice() {
		return mealPrice;
	}

	public void setMealPrice(BigDecimal mealPrice) {
		this.mealPrice = mealPrice;
	}

	public LocalDate getMealFromDate() {
		return mealFromDate;
	}
	public LocalDate getMealToDate() {
		return mealToDate;
	}
	public MealType getMealType() {
		return mealType;
	}
	public DietType getDietType() {
		return dietType;
	}
	public UUID getChildID() {
		return childID;
	}
}
