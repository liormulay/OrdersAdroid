package com.example.orders.utils;

import android.text.TextUtils;

import androidx.appcompat.widget.AppCompatEditText;

import com.example.orders.R;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {
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

    public static boolean validRequired(AppCompatEditText appCompatEditText){
        if (TextUtils.isEmpty(appCompatEditText.getText())){
            appCompatEditText.setError(appCompatEditText.getContext().getString(R.string.required_field_error_message));
            return false;
        }
        return true;
    }
}
