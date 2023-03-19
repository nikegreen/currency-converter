package com.practicum.currencyconverter.presentation.converter;

public class CourseChangeVo {

    private final double differenceValue;
    private final double differencePercent;
    private final boolean isPositive;

    public CourseChangeVo(final double differenceValue, final double differencePercent, final boolean isPositive) {
        this.differenceValue = differenceValue;
        this.differencePercent = differencePercent;
        this.isPositive = isPositive;
    }

    public double getDifferenceValue() {
        return differenceValue;
    }

    public double getDifferencePercent() {
        return differencePercent;
    }

    public boolean isPositive() {
        return isPositive;
    }
}
