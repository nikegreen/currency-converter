package com.practicum.currencyconverter.data.models;

import com.practicum.currencyconverter.R;

public class USD extends Currency {

    @Override
    public int nameRes() {
        return R.string.currency_united_states;
    }

    @Override
    public int iconRes() {
        return R.drawable.ic_usa;
    }

    @Override
    public String getCode() {
        return "USD";
    }
}