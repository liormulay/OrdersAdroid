package com.example.orders.utils;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FormatUtils {
    /**
     *
     * @param price the price as float
     * @return the price as a string round 2 digits
     */
    @NotNull
    public static String getRoundPrice(float price){
        String text = String.valueOf(price);
        BigDecimal bigDecimal = new BigDecimal(text);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_DOWN);
        return String.valueOf(bigDecimal.floatValue());
    }
}
