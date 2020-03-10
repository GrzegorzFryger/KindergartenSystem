package pl.edu.pja.prz.payments.model;

import pl.edu.pja.prz.payments.model.enums.TypeDiscount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

public interface DiscountCalculator {
	BigDecimal getBaseAmount();

	Set<Discount> getDiscounts();

	default BigDecimal calculateAmountWithDiscount() {
		if (getDiscounts().isEmpty()) {
			return getBaseAmount();
		} else {
			return getDiscounts()
					.stream()
					.map(discount -> {
						BigDecimal valueOfDiscount;
						if (TypeDiscount.PERCENTAGE == discount.getTypeDiscount()) {
							valueOfDiscount = getBaseAmount().multiply(
									discount.getValue().divide(BigDecimal.valueOf(100))
							).setScale(2, RoundingMode.CEILING);
						} else {
							valueOfDiscount = discount.getValue()
									.setScale(2, RoundingMode.CEILING);
						}
						return valueOfDiscount;
					})
					.reduce(getBaseAmount(), BigDecimal::subtract);
		}
	}
}
