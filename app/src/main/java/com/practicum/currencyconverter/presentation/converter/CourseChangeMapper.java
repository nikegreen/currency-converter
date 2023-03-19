package com.practicum.currencyconverter.presentation.converter;

public class CourseChangeMapper {

    public CourseChangeVo map(final double current, final double previous) {
        final double differenceValue = current - previous;
        final double differencePercent = Math.abs(current / previous * 100 - 100);

        return new CourseChangeVo(differenceValue, differencePercent, current >= previous);
    }
}
