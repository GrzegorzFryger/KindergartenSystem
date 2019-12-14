package pl.edu.pja.prz.tuition.model;

import pl.edu.pja.prz.tuition.model.enums.TypeRebate;

import java.math.BigDecimal;
import java.util.Set;

public interface DiscountCalculator {
	BigDecimal getAmount();

	Set<RebatePolicy> getRebatePolicy();

	default BigDecimal calculateAmountWithDiscount() {
		return getRebatePolicy()
				.stream()
				.map(rebatePolicy -> {
					BigDecimal valueOfDiscount;
					if (rebatePolicy.getTypeRebate() == TypeRebate.PERCENTAGE) {
						valueOfDiscount = getAmount().multiply(
								rebatePolicy.getValue().divide(BigDecimal.valueOf(100))
						);
					} else {
						valueOfDiscount = rebatePolicy.getValue();
					}
					return valueOfDiscount;
				})
				.reduce(getAmount(), BigDecimal::subtract);
	}
}
