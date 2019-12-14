package pl.edu.pja.prz.tuition.model;

import pl.edu.pja.prz.tuition.model.enums.TypeRebate;

import java.math.BigDecimal;
import java.util.Set;

public interface DiscountCalculator {
	BigDecimal getAmount();

	Set<Rebate> getRebates();

	default BigDecimal calculateAmountWithDiscount() {
		return getRebates()
				.stream()
				.map(rebate -> {
					BigDecimal valueOfDiscount;
					if (rebate.getTypeRebate() == TypeRebate.PERCENTAGE) {
						valueOfDiscount = getAmount().multiply(
								rebate.getValue().divide(BigDecimal.valueOf(100))
						);
					} else {
						valueOfDiscount = rebate.getValue();
					}
					return valueOfDiscount;
				})
				.reduce(getAmount(), BigDecimal::subtract);
	}
}
