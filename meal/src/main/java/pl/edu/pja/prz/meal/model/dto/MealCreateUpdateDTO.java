package pl.edu.pja.prz.meal.model.dto;

import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.enums.MealStatus;
import pl.edu.pja.prz.meal.model.enums.MealType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class MealCreateUpdateDTO {

	private BigDecimal mealPrice;
	private List<MealType> mealTypes;
	private UUID childID;
	private LocalDate mealFromDate;
	private LocalDate mealToDate;

	public MealCreateUpdateDTO(BigDecimal mealPrice, List<MealType> mealTypes, UUID childID,
							   LocalDate mealFromDate, LocalDate mealToDate) {
		this.mealPrice = mealPrice;
		this.mealTypes = mealTypes;
		this.childID = childID;
		this.mealFromDate = mealFromDate;
		this.mealToDate = mealToDate;
	}

	public static Meal createMealFactory(MealCreateUpdateDTO dto) {
		return new Meal(dto.getMealPrice(),
				LocalDateTime.of(dto.getMealFromDate(), LocalTime.NOON),
				LocalDateTime.of(dto.getMealToDate(), LocalTime.MIDNIGHT),
				MealStatus.ACTIVE,
				dto.getMealTypes(),
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
	public List<MealType> getMealTypes() {
		return mealTypes;
	}
	public UUID getChildID() {
		return childID;
	}
}
