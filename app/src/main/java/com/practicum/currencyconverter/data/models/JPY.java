package com.practicum.currencyconverter.data.models;

import com.practicum.currencyconverter.R;

public class JPY extends Currency {

    @Override
    public int nameRes() {
        return R.string.currency_japan;
    }

    @Override
    public int iconRes() {
        return R.drawable.ic_japan;
    }

    @Override
    public String getCode() {
        return "JPY";
    }
}