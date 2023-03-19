package com.practicum.currencyconverter.presentation.converter;

import com.practicum.currencyconverter.data.models.Currency;
import com.practicum.currencyconverter.data.models.RUB;
import com.practicum.currencyconverter.data.models.USD;

import androidx.annotation.NonNull;

public class ConverterState {

    private final Currency fromCurrency;
    private final Currency toCurrency;
    private final double currencyCourse;
    private final double fromCurrencyInput;
    private final double toCurrencyInput;
    private final CourseChangeVo courseChangeVo;


    public static ConverterState DEFAULT = new ConverterState(new USD(), new RUB(), 0.0, 0.0, 0.0, new CourseChangeVo(0.0, 0.0, true));

    public ConverterState(
            final Currency fromCurrency,
            final Currency toCurrency,
            final double currencyCourse,
            final double fromCurrencyInput,
            final double toCurrencyInput,
            final CourseChangeVo courseChangeVo
    ) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.currencyCourse = currencyCourse;
        this.fromCurrencyInput = fromCurrencyInput;
        this.toCurrencyInput = toCurrencyInput;
        this.courseChangeVo = courseChangeVo;
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }

    public double getCurrencyCourse() {
        return currencyCourse;
    }

    public double getFromCurrencyInput() {
        return fromCurrencyInput;
    }

    public double getToCurrencyInput() {
        return toCurrencyInput;
    }

    public CourseChangeVo getCourseChangeVo() {
        return courseChangeVo;
    }

    static class Builder {

        private Currency fromCurrency;
        private Currency toCurrency;
        private double currencyCourse;
        private double fromCurrencyInput;
        private double toCurrencyInput;
        private CourseChangeVo courseChangeVo;

        public Builder(final ConverterState state) {
            this.fromCurrency = state.fromCurrency;
            this.toCurrency = state.toCurrency;
            this.currencyCourse = state.currencyCourse;
            this.fromCurrencyInput = state.fromCurrencyInput;
            this.toCurrencyInput = state.toCurrencyInput;
            this.courseChangeVo = state.courseChangeVo;
        }

        public Builder setFromCurrency(final Currency fromCurrency) {
            this.fromCurrency = fromCurrency;
            return this;
        }

        public Builder setToCurrency(final Currency toCurrency) {
            this.toCurrency = toCurrency;
            return this;
        }

        public Builder setCurrencyCourse(final double currencyCourse) {
            this.currencyCourse = currencyCourse;
            return this;
        }

        public Builder setFromCurrencyInput(final double fromCurrencyInput) {
            this.fromCurrencyInput = fromCurrencyInput;
            return this;
        }

        public Builder setToCurrencyInput(final double toCurrencyInput) {
            this.toCurrencyInput = toCurrencyInput;
            return this;
        }

        public Builder setCourseChangeVo(final CourseChangeVo courseChangeVo) {
            this.courseChangeVo = courseChangeVo;
            return this;
        }

        public ConverterState copy() {
            return new ConverterState(fromCurrency, toCurrency, currencyCourse, fromCurrencyInput, toCurrencyInput, courseChangeVo);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "ConverterState{" +
                "fromCurrency=" + fromCurrency +
                ", toCurrency=" + toCurrency +
                ", currencyCourse=" + currencyCourse +
                ", fromCurrencyInput=" + fromCurrencyInput +
                ", toCurrencyInput=" + toCurrencyInput +
                '}';
    }
}
