package com.practicum.currencyconverter.data.models;

import com.google.gson.annotations.SerializedName;

public class CurrencyRate {

    @SerializedName("Valute")
    private Valute valute;

    public Valute getValute() {
        return valute;
    }
}