package com.practicum.currencyconverter.data.models;

import com.practicum.currencyconverter.R;

public class EUR extends Currency {

    @Override
    public int nameRes() {
        return R.string.currency_european_union;
    }

    @Override
    public int iconRes() {
        return R.drawable.ic_euro;
    }

    @Override
    public String getCode() {
        return "EUR";
    }
}