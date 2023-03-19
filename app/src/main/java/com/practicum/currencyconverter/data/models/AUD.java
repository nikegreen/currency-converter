package com.practicum.currencyconverter.data.models;

import com.practicum.currencyconverter.R;

public class AUD extends Currency {

    @Override
    public int nameRes() {
        return R.string.currency_australia;
    }

    @Override
    public int iconRes() {
        return R.drawable.ic_australia;
    }

    @Override
    public String getCode() {
        return "AUD";
    }
}