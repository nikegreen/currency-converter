package com.practicum.currencyconverter.ui.strings;

import java.util.Locale;

public class NumberFormatter {

    private static final String FORMAT_2_DIGIT_AFTER_COMA = "%.2f";
    private static final String FORMAT_WITHOUT_DIGIT_AFTER_COMA = "%.0f";

    public static String toString2Digit(final double number) {
        final String format = round(number) % 1 == 0 ? FORMAT_WITHOUT_DIGIT_AFTER_COMA : FORMAT_2_DIGIT_AFTER_COMA;
        return String.format(Locale.ENGLISH, format, number);
    }

    private static double round(double value) {
        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
