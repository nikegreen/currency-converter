package com.practicum.currencyconverter.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

abstract public class Currency implements Serializable {

    @StringRes
    public abstract int nameRes();

    @DrawableRes
    public abstract int iconRes();

    public abstract String getCode();

    @SerializedName("Value")
    private double value;

    @SerializedName("Previous")
    private double previous;

    @SerializedName("Nominal")
    private int nominal;

    public double getValue() {
        return value;
    }

    public double getPrevious() {
        return previous;
    }

    public int getNominal() {
        return nominal;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Currency currency = (Currency) o;
        return Double.compare(currency.value, value) == 0 && Double.compare(currency.previous, previous) == 0 && nominal == currency.nominal &&
                Objects.equals(getCode(), currency.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), value, previous, nominal);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + getCode() + '\'' +
                ", value=" + value +
                ", previous=" + previous +
                ", nominal=" + nominal +
                '}';
    }
}
