package com.practicum.currencyconverter.data.models;

import com.practicum.currencyconverter.R;

public class PLN extends Currency {

    @Override
    public int nameRes() {
        return R.string.currency_poland;
    }

    @Override
    public int iconRes() {
        return R.drawable.ic_poland;
    }

    @Override
    public String getCode() {
        return "PLN";
    }
}