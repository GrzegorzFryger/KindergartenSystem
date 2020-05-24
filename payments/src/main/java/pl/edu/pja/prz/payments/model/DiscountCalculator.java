package pl.edu.pja.prz.payments.model;

import pl.edu.pja.prz.payments.model.enums.TypeDiscount;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface DiscountCalculator {
    BigDecimal getBaseAmount();

    Discount getDiscount();

    default BigDecimal calculateAmountWithDiscount() {
        BigDecimal result = getBaseAmount();
        if (getDiscount() == null) {
            // Do nothing - since discount is not found
        } else {
            if (TypeDiscount.PERCENTAGE == getDiscount().getTypeDiscount()) {
                BigDecimal multiplier = getDiscount().getValue().divide(BigDecimal.valueOf(100));
                result = result.subtract(result.multiply(multiplier).setScale(2, RoundingMode.CEILING));
            } else {
                result = result.subtract(getDiscount().getValue()
                        .setScale(2, RoundingMode.CEILING));
            }
        }
        return result;
    }
}

