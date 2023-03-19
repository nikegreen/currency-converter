package com.practicum.currencyconverter.data.models;

import com.practicum.currencyconverter.R;

public class CHF extends Currency {

    @Override
    public int nameRes() {
        return R.string.currency_switzerland;
    }

    @Override
    public int iconRes() {
        return R.drawable.ic_switzerland;
    }

    @Override
    public String getCode() {
        return "CHF";
    }
}