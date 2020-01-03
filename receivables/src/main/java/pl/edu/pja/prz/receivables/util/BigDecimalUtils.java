package pl.edu.pja.prz.receivables.util;

import java.math.BigDecimal;

public class BigDecimalUtils {
    private BigDecimalUtils() {

    }

    public static boolean isPositive(BigDecimal value) {
        return value.compareTo(BigDecimal.ZERO) > 0;
    }
}
