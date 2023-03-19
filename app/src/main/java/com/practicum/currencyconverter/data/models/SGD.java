package com.practicum.currencyconverter.data.models;

import com.practicum.currencyconverter.R;

public class SGD extends Currency {

    @Override
    public int nameRes() {
        return R.string.currency_singapore;
    }

    @Override
    public int iconRes() {
        return R.drawable.ic_singapore;
    }

    @Override
    public String getCode() {
        return "SGD";
    }
}