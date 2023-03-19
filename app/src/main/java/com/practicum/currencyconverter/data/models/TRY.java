package com.practicum.currencyconverter.data.models;

import com.practicum.currencyconverter.R;

public class TRY extends Currency {

    @Override
    public int nameRes() {
        return R.string.currency_turkey;
    }

    @Override
    public int iconRes() {
        return R.drawable.ic_turkey;
    }

    @Override
    public String getCode() {
        return "TRY";
    }
}