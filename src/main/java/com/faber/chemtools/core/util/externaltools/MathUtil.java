package com.faber.chemtools.core.util.externaltools;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtil {
    public static double fourthProportional(double a1, double b1, double a2) {
        BigDecimal bdA1 = new BigDecimal(Double.toString(a1));
        BigDecimal bdB1 = new BigDecimal(Double.toString(b1));
        BigDecimal bdA2 = new BigDecimal(Double.toString(a2));

        return bdB1.multiply(bdA2)
                .divide(bdA1, 4, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
